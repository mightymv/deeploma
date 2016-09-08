import {Component, OnInit} from "@angular/core";
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

export class OddsPageComponent implements OnInit {

    matches: Array<Match>;
    matchesBasket: Array<Match>;

    constructor(private http: Http) {
    }

    getHeader() {
        let headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append('Access-Control-Allow-Origin', '*');
        return headers;
    }

    ngOnInit() {
        this.http.get('http://192.168.182.198:8080/offer/2016-08-10', {headers: this.getHeader()})
            .map((res: Response) => res.json())
            .map((matches: Array<any>) => {  return Deserializer.deserialize(matches); })
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
}