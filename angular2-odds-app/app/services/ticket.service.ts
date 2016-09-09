import {Injectable, EventEmitter, OnDestroy} from '@angular/core';
import {Http, Headers} from "@angular/http";
import {PayInTicketRow, PayTicketRequest} from "../dto/payTicket";
import {UserService} from "./user.service";
import {LocalStorageUser} from "../dto/login";

@Injectable()
export class TicketService {

    public ticketChangeEvent$: EventEmitter<PayInTicketRow>;
    public ticketCleanEvent$: EventEmitter<Boolean>;
    public ticketRows: Array<PayInTicketRow> = [];

    constructor(private http: Http, private userService: UserService) {
        this.ticketChangeEvent$ = new EventEmitter<PayInTicketRow>();
        this.ticketCleanEvent$ = new EventEmitter<Boolean>();
    }

    /**
     * Dohvata sve tikete.
     * @returns {Array<PayInTicketRow>}
     */
    public getTicketRows(): Array<PayInTicketRow> {
        return this.ticketRows;
    }

    /**
     * Dodaje zadati red u tiket.
     * @param ticketRow
     */
    public addTicketRow(ticketRow: PayInTicketRow): void {

        if(ticketRow !== null || ticketRow !== undefined) {

            this.ticketRows.push(ticketRow);
            this.ticketChangeEvent$.emit(ticketRow);
        }
    }

    /**
     * Uklanja zadati red iz liste tiketa.
     * @param ticketRow
     */
    public removeTicketRow(ticketRow: PayInTicketRow): void {

        if(ticketRow !== null || ticketRow !== undefined) {

            this.ticketRows = this.ticketRows.filter((removedRow) => removedRow.matchId !== ticketRow.matchId);
            this.ticketChangeEvent$.emit(ticketRow);
        }
    }

    /**
     * Uklanja sve redove tiketa.
     */
    public removeAllTicketRows() {
        this.ticketRows = [];
        this.ticketCleanEvent$.emit(true);
    }

    /**
     * Uplata tiketa.
     */
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

    public getTickets() {

        let userId = this.userService.getUserFromLocalStorage().id;
        let tickets = [];

        // this.http.get(`http://192.168.182.198:8082/${userId}/tickets`)
        //     .map(res => res.json())
        //     .toPromise(
        //         res => {
        //             console.log("Fetching tickets success :)");
        //             tickets = res;
        //         },
        //         err => console.log("Fetching tickets failed !!! " + err),
        //         () => console.log("Fetching tickets completed.")
        //     );
    }
}