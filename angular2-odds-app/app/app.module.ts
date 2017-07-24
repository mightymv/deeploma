import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {AppComponent} from "./components/app.component";
import {CookieService} from "angular2-cookie/services/cookies.service";
import {UserService} from "./services/user.service";
import {TicketService} from "./services/ticket.service";
import {OddsPageComponent} from "./components/odds.page.component";
import {OddsComponent} from "./components/odds/odds.component";
import {LoginComponent} from "./components/login/login.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {RouterModule, Routes} from "@angular/router";
import {NavigationComponent} from "./components/navigation/nav.component";
import {TicketViewerComponent} from "./components/ticket-viewer/ticketViewer.component";
import {RecomendationsComponent} from "./components/recomendations/recomendations.component";
import {MatchesTableComponent} from "./components/tables/matches/matchesTable.component";
import {OddsTableComponent} from "./components/tables/odds/oddsTable.component";
import {GameFilterPipe} from "./pipes/game-filter.pipe";
import {MessageModalComponent} from "./components/messagesModal/messageModal.component";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {AuthService} from "./services/auth.service";
import {StandingsComponent} from "./components/standings/standings.component";

const routes: Routes = [
    {
        path: '',
        component: OddsPageComponent,
        children: [
            {path: '', component: OddsComponent},
            {path: 'odds', component: OddsComponent},
            {path: 'dashboard', component: DashboardComponent, canActivate: [AuthService]},
            {path: 'standings', component: StandingsComponent, canActivate: [AuthService]},
        ]
    },
    {
        path: 'login',
        component: LoginComponent
    }
];

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        RouterModule.forRoot(routes)
    ],
    declarations: [
        AppComponent,
        OddsPageComponent,
        OddsComponent,
        LoginComponent,
        DashboardComponent,
        NavigationComponent,

        GameFilterPipe,

        TicketViewerComponent,
        RecomendationsComponent,
        MatchesTableComponent,
        OddsTableComponent,
        MessageModalComponent,
        StandingsComponent
    ],
    providers: [TicketService, UserService, CookieService, AuthService],
    bootstrap: [AppComponent]
})
export class AppModule {
}