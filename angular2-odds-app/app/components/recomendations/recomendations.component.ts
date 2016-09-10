import { Component, OnInit } from '@angular/core';
import {Http, Response} from "@angular/http";
import {UserService} from "../../services/user.service";

@Component({
    moduleId: module.id,
    selector: 'recomendations',
    templateUrl: 'recomendations.component.html',
    styleUrls: ['recomendations.component.css']
})
export class RecomendationsComponent implements OnInit {

    private userId: number;
    private userRecommendations: Array<number>;

    constructor(private http: Http, private userService: UserService) {
    }

    ngOnInit(): void {
        this.userId = this.userService.getUserFromLocalStorage().id;
        this.loadRecommendations();
    }

    loadRecommendations() {
        this.http.get(`http://192.168.182.198:8082/${this.userId}/recommendation`)
            .map((res: Response) => res.json())
            .toPromise()
            .then(res => {
                this.userRecommendations = res;
            })
            .catch(err => console.log("User recommendations loading FAILED !!! " + err));
    }
}