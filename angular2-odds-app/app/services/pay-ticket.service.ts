import {Injectable, EventEmitter} from '@angular/core';
import {Http} from "@angular/http";
import {TicketRow} from "../dto/payTicket";

@Injectable()
export class PayTicketService {

    public ticketChangeEvent$: EventEmitter<TicketRow>;
    public ticketRows: Array<TicketRow> = [];

    constructor(http: Http) {
        this.ticketChangeEvent$ = new EventEmitter<TicketRow>();
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
}