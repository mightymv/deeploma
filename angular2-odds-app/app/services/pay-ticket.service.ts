import {Injectable, EventEmitter, OnDestroy} from '@angular/core';
import {Http, Headers} from "@angular/http";
import {TicketRow, PayTicketRequest} from "../dto/payTicket";
import {UserService} from "./user.service";
import {LocalStorageUser} from "../dto/login";

@Injectable()
export class PayTicketService {

    public ticketChangeEvent$: EventEmitter<TicketRow>;
    public ticketCleanEvent$: EventEmitter<Boolean>;
    public ticketRows: Array<TicketRow> = [];

    constructor(private http: Http, private userService: UserService) {
        this.ticketChangeEvent$ = new EventEmitter<TicketRow>();
        this.ticketCleanEvent$ = new EventEmitter<Boolean>();
    }

    public getTicketRows(): Array<TicketRow> {
        return this.ticketRows;
    }

    public addTicketRow(ticketRow: TicketRow): void {

        if(ticketRow !== null || ticketRow !== undefined) {

            this.ticketRows.push(ticketRow);
            this.ticketChangeEvent$.emit(ticketRow);
        }
    }

    public removeTicketRow(ticketRow: TicketRow): void {

        if(ticketRow !== null || ticketRow !== undefined) {

            this.ticketRows = this.ticketRows.filter((removedRow) => removedRow.matchId !== ticketRow.matchId);
            this.ticketChangeEvent$.emit(ticketRow);
        }
    }

    public removeAllTicketRows() {
        this.ticketRows = [];
        this.ticketCleanEvent$.emit(true);
    }

    public payTicket() {

        let user:LocalStorageUser = this.userService.getUserFromLocalStorage();

        let payRequest: PayTicketRequest = new PayTicketRequest(user.id, this.ticketRows);

        let headers = new Headers();
        headers.append("Content-Type", "application/json");
        headers.append("X-Auth-Token", user.token);

        this.http.put("http://local.angular2odds.com:8080/ticket/add",
            JSON.stringify(payRequest), {headers: headers, withCredentials: false})
            .map(res => res.text())
            .subscribe(
                res => {
                    console.log("Payin ticket successful :) " + res);
                },
                err => console.log("Payin ticket failed !!! " + err),
                () => console.log("Payin ticket completed.")
            );
    }
}