import {Component, OnInit, OnDestroy} from "@angular/core";
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

    constructor(private http: Http, private userService: UserService) {
        this.userId = this.userService.getUser().id;
    }

    ngOnInit(): void {
        this.loadRecommendations();
    }

    ngOnDestroy(): void {

        if (this.userId === null) {
            return;
        }

        this.recommendationPoller.unsubscribe();
    }

    loadRecommendations() {

        if(this.userId === null) {
            return;
        }

        this.recommendationPoller = Observable.timer(300, 5000)
            .timeInterval()
            .switchMap(() => {
                return this.http
                    .get(`http://192.168.182.198:8082/${this.userId}/recommendation`)
            })
            .map((res: Response) => res.json())
            .subscribe(
                res => {
                    this.userRecommendations = this.findRecommendations(res);
                },
                err => console.log("User recommendations loading FAILED !!! " + err)
            );
    }

    findRecommendations(response: Array<any>): Array<RecommendMatch> {

        let recommendations = new Array<RecommendMatch>();
        let currentRecommend;

        response.forEach(matchId => {
            let matchRow = jQuery('matches-table').find('[data-match-id=' + matchId + ']').first();

            if(matchRow === undefined) {
                return;
            }

            let participants:string = matchRow.children('.home').text() + ' - ' + matchRow.children('.visitor').text();
            currentRecommend = new RecommendMatch(matchId, participants);
            recommendations.push(currentRecommend);
        });

        return recommendations;
    }
}