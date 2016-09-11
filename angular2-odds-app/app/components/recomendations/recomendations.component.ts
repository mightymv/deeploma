import {Component, OnInit, OnDestroy, Renderer} from "@angular/core";
import {Http, Response} from "@angular/http";
import {UserService} from "../../services/user.service";
import {Observable} from "rxjs/Rx";
import {RecommendMatch} from "../../dto/offer";

declare var jQuery:any;

@Component({
    moduleId: module.id,
    selector: 'recomendations',
    templateUrl: 'recomendations.component.html',
    styleUrls: ['recomendations.component.css']
})
export class RecomendationsComponent implements OnInit, OnDestroy {

    private userId: number;
    private userRecommendations: Array<RecommendMatch> = [];
    private recommendationPoller;

    constructor(private http: Http,
                private userService: UserService,
                private renderer:Renderer) {
    }

    ngOnInit(): void {
        this.userId = this.userService.getUserFromLocalStorage().id;
        this.loadRecommendations();
    }

    ngOnDestroy(): void {
        this.recommendationPoller.unsubscribe();
    }

    loadRecommendations() {

        this.recommendationPoller = Observable.interval(5000)
            .switchMap(() => this.http.get(`http://192.168.182.198:8082/${this.userId}/recommendation`))
            .map((res: Response) => res.json())
            .subscribe(
                res => {
                    this.userRecommendations = this.findRecommendations(res);
                },
                err => err => console.log("User recommendations loading FAILED !!! " + err)
            );
    }

    findRecommendations(response: Array<any>): Array<RecommendMatch> {

        let recommendations = new Array<RecommendMatch>();
        let currentRecommend;

        response.forEach(matchId => {
            let temp = jQuery(document.getElementsByTagName('matches-table')).find('[data-match-id=' + matchId + ']')[0];
            let participants:string = temp.children[2].innerText + ' - ' + temp.children[3].innerText;

            currentRecommend = new RecommendMatch(matchId, participants);

            recommendations.push(currentRecommend);
        });

        return recommendations;
    }
}