
export class TopUser {

    public dateOf: Date;
    public ticketId: number;
    public username: string;
    public userId: number;
    public bestResult: number;

    constructor(topUser: TopUser) {
        this.dateOf = topUser.dateOf;
        this.ticketId = topUser.ticketId;
        this.username = topUser.username;
        this.userId = topUser.userId;
        this.bestResult = topUser.bestResult;
    }
}