import {Component, OnInit} from "@angular/core";
import {UserService} from "../../services/user.service";
import {TopUser} from "../../dto/standings";

@Component({
    moduleId: module.id,
    selector: 'standings',
    templateUrl: 'standings.component.html',
    styleUrls: ['standings.component.css']
})
export class StandingsComponent implements OnInit {

    private topPlayers: Array<TopUser>;

    constructor(private userService: UserService) {
    }

    ngOnInit(): void {
        this.userService.standings()
            .then(
                (topPlayers: Array<TopUser>) => {
                    console.log("Standings SUCCESS :) " + topPlayers);
                    this.topPlayers = topPlayers;
                })
            .catch(
                err => console.log("Standings failed !!! " + err)
            );
    }
}