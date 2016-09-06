import {Injectable, EventEmitter, OnDestroy} from '@angular/core';
import {Http} from "@angular/http";
import {TicketRow} from "../dto/payTicket";

@Injectable()
export class PayTicketService {

    public ticketChangeEvent$: EventEmitter<TicketRow>;
    public ticketCleanEvent$: EventEmitter<Boolean>;
    public ticketRows: Array<TicketRow> = [];

    constructor(http: Http) {
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
}