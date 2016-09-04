
// Offer DTO classes

export class Match {

    constructor(public id: number,
                public startTime: number,
                public competition: Competition,
                public teamHome: Team,
                public teamVisitor: Team,
                public results: Array<Result>,
                public matchStatus: string,
                public odds: Array<Odd>
    ) {}
}

export class Competition {

    constructor(public id: number,
                public name: string,
                public sport: Sport) {}
}

export class Sport {

    constructor(public id: number,
                public name: string) {}
}

export class Team {

    constructor(public id: number,
                public name: string) {}
}

export class Result {

    constructor() {}
}

export class Odd {

    constructor(public id: number,
                public subGame: SubGame,
                public match: Match,
                public value: number,
                public betOddStatus: string) {}
}

export class Game {

    constructor(public id: number,
                public name: string) {}
}

export class SubGame {

    constructor(public name: string,
                public id: number,
                public shortName: string,
                public game: Game) {}
}