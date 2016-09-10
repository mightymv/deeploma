import {Component, OnInit, OnDestroy} from "@angular/core";
import {NavigationComponent} from "./navigation/nav.component";
import {TicketViewerComponent} from "./ticket-viewer/ticketViewer.component";
import {RecomendationsComponent} from "./recomendations/recomendations.component";
import {MessageModalComponent} from "./messagesModal/messageModal.component";
import {MatchesTableComponent} from "./tables/matches/matchesTable.component";
import {OddsTableComponent} from "./tables/odds/oddsTable.component";

import {Response, Http, Headers} from "@angular/http";
import {CORE_DIRECTIVES, DatePipe} from "@angular/common";
import {Deserializer} from "../utils/Deserializer";
import {Match} from "../dto/offer/offer";
import {Observable} from "rxjs/Rx";

@Component({
    selector: 'odds-page-app',
    templateUrl: 'app/components/odds.page.component.html',
    styleUrls: ["app/components/odds.page.component.css"],
    pipes: [DatePipe],
    directives: [
        NavigationComponent,
        TicketViewerComponent,
        RecomendationsComponent,
        MessageModalComponent,
        MatchesTableComponent,
        OddsTableComponent,
        CORE_DIRECTIVES
    ]
})

export class OddsPageComponent implements OnInit, OnDestroy {

    matches: Array<Match>;
    matchesBasket: Array<Match>;
    private mouseMoveEvents$;
    private clickEvents$;

    constructor(private http: Http) {

        this.mouseMoveEvents$ = Observable.fromEvent<MouseEvent>(document, 'mousemove')
            .sampleTime(3000)
            .distinctUntilChanged()
            .subscribe(x => {
                let matchId = x.srcElement.parentElement.dataset['matchId'];
                if (matchId !== undefined) {
                    console.log(Number.parseInt(matchId));
                }
            });

        this.clickEvents$ = Observable.fromEvent<MouseEvent>(document, 'click')
            .subscribe(x => {
                let matchId = x.srcElement.parentElement.dataset['matchId'];
                if (matchId !== undefined) {
                    console.log("Click " + Number.parseInt(matchId));
                }
            });
    }

    ngOnInit() {

        let headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append('Access-Control-Allow-Origin', '*');

        this.http.get('http://192.168.182.198:8080/offer/2016-08-10', {headers: headers})
            .map((res: Response) => res.json())
            .map((matches: Array<any>) => {
                return Deserializer.deserialize(matches);
            })
            .subscribe(
                loadedMatches => {
                    this.matches = loadedMatches.filter(match => match.competition.sport.name === 'Football');
                    this.matchesBasket = loadedMatches.filter(match => match.competition.sport.name === 'Basketball');
                    console.log(this.matches);
                    console.log(this.matchesBasket);
                },
                err => console.log("Offer NOT LOADED!!! " + err),
                () => console.log('Result loaded')
            );
    }

    ngOnDestroy() {
        this.mouseMoveEvents$.unsubscribe();
        this.clickEvents$.unsubscribe();
    }
}