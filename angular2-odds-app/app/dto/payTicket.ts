export class PayTicketRequest {

    constructor(public userId: number,
                public ticketRows: Array<TicketRow>,
                public cumulativeOdd: number) {
    }
}

export class TicketRow {

    constructor(public betOddId: number,
                public matchId: number,
                public subGameShortName: string,
                public odd: number) {
    }


    public toString(): string {
        return "betOddId: " + this.betOddId +
            ", matchId: " + this.matchId +
            ", subGameShorName: " + this.subGameShortName +
            ", odd: " + this.odd;
    }
}