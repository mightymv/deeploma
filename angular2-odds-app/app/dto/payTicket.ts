import {Match} from "./offer/offer";

export class PayTicketRequest {

    public cumulativeOdd: number = 1;

    constructor(public userId: number,
                public ticketRows: Array<PayInTicketRow>) {
        this.ticketRows.forEach(
            ticket => this.cumulativeOdd*=ticket.odd );
    }
}

export class PayInTicketRow {

    constructor(public betOddId: number,
                public matchId: number,
                public subGameShortName: string,
                public odd: number,
                public competitors: string) {
    }


    public toString(): string {
        return "betOddId: " + this.betOddId +
            ", matchId: " + this.matchId +
            ", subGameShorName: " + this.subGameShortName +
            ", odd: " + this.odd + ", competitors: " + this.competitors;
    }
}


export class Ticket {

    constructor(public id: number,
                public rows: Array<TicketRow>,
                public time: number,
                public status: string) {}
}

export class TicketRow {

    constructor(public id: number,
                public match: Match,
                public betOddId: number,
                public subGameShortName: string,
                public status: string) {}
}

