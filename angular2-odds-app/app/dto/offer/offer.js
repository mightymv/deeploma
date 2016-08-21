"use strict";
var Match = (function () {
    function Match(min, home, visitor, HT, FT) {
        this.min = min;
        this.home = home;
        this.visitor = visitor;
        this.HT = HT;
        this.FT = FT;
    }
    return Match;
}());
exports.Match = Match;
var Odds = (function () {
    // constructor(top:string[], toptwo:string[][]) {
    //     this.top = top;
    //     this.toptwo = toptwo;
    // }
    function Odds(odds) {
        this.top = odds.top;
        this.toptwo = odds.toptwo;
    }
    return Odds;
}());
exports.Odds = Odds;
var OfferData = (function () {
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
    function OfferData(obj) {
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
    return OfferData;
}());
exports.OfferData = OfferData;
//# sourceMappingURL=offer.js.map