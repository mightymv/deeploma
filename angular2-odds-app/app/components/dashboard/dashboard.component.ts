import {Component, OnInit} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Ticket} from "../../dto/payTicket";
import {UserService} from "../../services/user.service";

@Component({
    moduleId: module.id,
    selector: 'dashboard',
    templateUrl: 'dashboard.component.html'
})
export class DashboardComponent implements OnInit {

    private title: string = "User tickets";
    private userId: number;
    private userTickets: Array<Ticket>;

    constructor(private http: Http, private userService: UserService) {
    }

    ngOnInit(): void {
        this.userId = this.userService.getUserFromLocalStorage().id;
        this.loadTickets();
    }

    loadTickets(): void {

        this.http.get(`http://192.168.182.198:8082/${this.userId}/tickets`)
            .toPromise()
            .then(res => {
                this.userTickets = res.json();
            })
            .catch(err => console.log("User tickes loading FAILED !!! " + err));
    }
}