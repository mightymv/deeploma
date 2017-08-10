import {Injectable, EventEmitter} from "@angular/core";
import {Http, Headers} from "@angular/http";
import {PayInTicketRow, PayTicketRequest, PayInInfo} from "../dto/payTicket";
import {UserService} from "./user.service";
import {User} from "../dto/login";
import {Observable} from "rxjs";

@Injectable()
export class TicketService {

    public payinInfo$: EventEmitter<PayInInfo>;
    public ticketChangeEvent$: EventEmitter<PayInTicketRow>;
    public ticketCleanEvent$: EventEmitter<Boolean>;
    public ticketRows: Array<PayInTicketRow> = [];

    constructor(private http: Http, private userService: UserService) {
        this.payinInfo$ = new EventEmitter<PayInInfo>();
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

            this.ticketRows = this.ticketRows.filter(removedRow => removedRow.betOddId !== ticketRow.betOddId);
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
    public payTicket(): Observable<any> {

        let user:User = this.userService.getUser();

        let payRequest: PayTicketRequest = new PayTicketRequest(user.id, this.ticketRows);

        let headers = new Headers();
        headers.append("Content-Type", "application/json");
        headers.append("X-Auth-Token", user.token);

        return this.http.put("http://192.168.182.198:8081/ticket/add",
            JSON.stringify(payRequest), {headers: headers, withCredentials: false})
            .map(res => res.text());
    }

    public getTickets(): Observable<any> {

        let userId = this.userService.getUser().id;
        return this.http.get(`http://192.168.182.198:8082/${userId}/tickets`);
    }
}