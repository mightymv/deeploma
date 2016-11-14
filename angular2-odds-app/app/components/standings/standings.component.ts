import {Component, OnInit} from "@angular/core";
import {UserService} from "../../services/user.service";

@Component({
    moduleId: module.id,
    selector: 'standings',
    templateUrl: 'standings.component.html',
    styleUrls: ['standings.component.css']
})
export class StandingsComponent implements OnInit {

    constructor(private userService: UserService) {
    }

    ngOnInit(): void {
        // this.userService.standings().;
    }

}