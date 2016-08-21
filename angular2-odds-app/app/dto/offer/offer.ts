

export class Match {
    min:number;
    home:string;
    visitor:string;
    HT:string;
    FT:string;
    
    constructor(min: number, home: string, visitor: string, HT: string, FT: string) {
        this.min = min;
        this.home = home;
        this.visitor = visitor;
        this.HT = HT;
        this.FT = FT;
    }
}

export class Odds {
    top:string[];
    toptwo:string[][];

    // constructor(top:string[], toptwo:string[][]) {
    //     this.top = top;
    //     this.toptwo = toptwo;
    // }

    constructor(odds: Odds) {
        this.top = odds.top;
        this.toptwo = odds.toptwo;
    }
}

export class OfferData {
    status:string;
    uuid:string;
    matches:Match[];
    odds:Odds;
    cet_time:string;
    est_end:number;
    gid:string;
    serial:number;
    sport:string;
    //
    // constructor(status:string, uuid:string, matches:Match[], odds:Odds, cet_time:string, est_end:number, gid:string, serial:number, sport:string) {
    //     this.status = status;
    //     this.uuid = uuid;
    //     this.matches = matches;
    //     this.odds = odds;
    //     this.cet_time = cet_time;
    //     this.est_end = est_end;
    //     this.gid = gid;
    //     this.serial = serial;
    //     this.sport = sport;
    // }


    constructor(obj:OfferData) {
        this.status = obj.status;
        this.uuid = obj.uuid;
        this.matches = obj.matches;
        this.odds = obj.odds;
        this.cet_time = obj.cet_time;
        this.est_end = obj.est_end;
        this.gid = obj.gid;
        this.serial = obj.serial;
        this.sport = obj.sport;
    }
}