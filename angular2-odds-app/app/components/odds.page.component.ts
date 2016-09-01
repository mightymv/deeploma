import {Component} from '@angular/core';
import {NavigationComponent} from "./navigation/nav.component";
import {TicketViewerComponent} from "./ticket-viewer/ticketViewer.component";
import {RecomendationsComponent} from "./recomendations/recomendations.component";
import {MessageModalComponent} from "./messagesModal/messageModal.component";
import {MatchesTableComponent} from "./tables/matches/matchesTable.component";
import {ResultsTableComponent} from "./tables/results/resultsTable.component";

@Component({
    selector: 'odds-page-app',
    templateUrl: 'app/components/odds.page.component.html',
    styleUrls: ["app/components/main-page.css"],
    directives: [
        NavigationComponent,
        TicketViewerComponent,
        RecomendationsComponent,
        MessageModalComponent,
        MatchesTableComponent,
        ResultsTableComponent
    ]
})

export class OddsPageComponent {
}