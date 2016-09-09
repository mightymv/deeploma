// Offer DTO classes
"use strict";
var Match = (function () {
    function Match(id, startTime, competition, teamHome, teamVisitor, matchStatus, results, odds) {
        this.id = id;
        this.startTime = startTime;
        this.competition = competition;
        this.teamHome = teamHome;
        this.teamVisitor = teamVisitor;
        this.matchStatus = matchStatus;
        this.results = results;
        this.odds = odds;
    }
    return Match;
}());
exports.Match = Match;
var Competition = (function () {
    function Competition(id, name, sport) {
        this.id = id;
        this.name = name;
        this.sport = sport;
    }
    return Competition;
}());
exports.Competition = Competition;
var Sport = (function () {
    function Sport(id, name) {
        this.id = id;
        this.name = name;
    }
    return Sport;
}());
exports.Sport = Sport;
var Team = (function () {
    function Team(id, name) {
        this.id = id;
        this.name = name;
    }
    return Team;
}());
exports.Team = Team;
var Result = (function () {
    function Result() {
    }
    return Result;
}());
exports.Result = Result;
var Odd = (function () {
    function Odd(id, subGame, match, value, betOddStatus) {
        this.id = id;
        this.subGame = subGame;
        this.match = match;
        this.value = value;
        this.betOddStatus = betOddStatus;
    }
    return Odd;
}());
exports.Odd = Odd;
var Game = (function () {
    function Game(id, name) {
        this.id = id;
        this.name = name;
    }
    return Game;
}());
exports.Game = Game;
var SubGame = (function () {
    function SubGame(name, id, shortName, game) {
        this.name = name;
        this.id = id;
        this.shortName = shortName;
        this.game = game;
    }
    return SubGame;
}());
exports.SubGame = SubGame;
//# sourceMappingURL=offer.js.map