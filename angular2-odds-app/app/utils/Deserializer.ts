
import * as Offer from "../dto/offer";

export class Deserializer {
    constructor() {}

    /**
     * Deserialize matches from feed  /offer/
     * @param matches
     * @returns {Array<Match>}
     */
    public static deserialize(matches: Array<any>): Array<Offer.Match> {

        let matchesDTO: Array<Offer.Match> = [];

        if(matches) {
            matches.forEach((match) => {

                let competition = match.competition;
                let teamHome = match.teamHome;
                let teamVisitor = match.teamVisitor;
                let odds: Array<Offer.Odd> = [];

                match.odds.forEach((odd) => {

                    let currOdd = new Offer.Odd(
                        odd.id,
                        new Offer.SubGame(
                            odd.subGame.name,
                            odd.subGame.id,
                            odd.subGame.shortName,
                            new Offer.Game(odd.subGame.game.id, odd.subGame.game.name)
                        ),
                        null,
                        odd.value,
                        odd.betOddStatus
                    );

                    odds.push(currOdd);
                });

                // Veljko jooooooooooj bre
                Deserializer.swap(odds, 1, 2);
                Deserializer.swap(odds, 4, 5);

                matchesDTO.push(
                    new Offer.Match(
                        match.id,
                        new Date(match.startTime).getTime(),
                        new Offer.Competition(competition.id,
                            competition.name,
                            new Offer.Sport(competition.sport.id, competition.sport.name)),
                        new Offer.Team(teamHome.id, teamHome.name),
                        new Offer.Team(teamVisitor.id, teamVisitor.name),
                        match.matchStatus,
                        null,
                        odds
                    ));
            });
        }
        return matchesDTO;
    }

    private static swap(odds: Array<Offer.Odd>, one: number, two: number):void {
        let temp = odds[one];
        odds[one] = odds[two];
        odds[two] = temp;
    }
}