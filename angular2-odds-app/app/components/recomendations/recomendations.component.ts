import {Component, OnInit, OnDestroy} from "@angular/core";
import {Http, Response} from "@angular/http";
import {UserService} from "../../services/user.service";
import {Observable} from "rxjs/Rx";

@Component({
    moduleId: module.id,
    selector: 'recomendations',
    templateUrl: 'recomendations.component.html',
    styleUrls: ['recomendations.component.css']
})
export class RecomendationsComponent implements OnInit, OnDestroy {

    private userId: number;
    private userRecommendations: Array<number> = [];
    private recommendationPoller;

    constructor(private http: Http, private userService: UserService) {
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
                    this.userRecommendations = res;
                    console.log("Ucitani recommendation podaci " + res);
                },
                err => err => console.log("User recommendations loading FAILED !!! " + err)
            );
    }
}