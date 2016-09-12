import {Component, OnInit} from "@angular/core";
import {Ticket} from "../../dto/payTicket";
import {TicketService} from "../../services/ticket.service";

@Component({
    moduleId: module.id,
    selector: 'dashboard',
    templateUrl: 'dashboard.component.html'
})
export class DashboardComponent implements OnInit {

    private title: string = "User tickets";
    private userTickets: Array<Ticket> = [];

    constructor(private ticketService: TicketService) {
    }

    ngOnInit(): void {
        this.loadTickets();
    }

    loadTickets(): void {

        this.ticketService.getTickets()
            .toPromise()
            .then(res => this.userTickets = res.json())
            .catch(err => console.log("User tickets loading FAILED !!! " + err));
    }

    onToggleTicket(clickEvent$: any):void {

        let matchTable = clickEvent$.target.parentElement.cells[1].children[0];
        matchTable.hidden = !matchTable.hidden;
    }
}