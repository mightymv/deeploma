import {Component} from "@angular/core";
import {ROUTER_DIRECTIVES} from "@angular/router";
import {OddsPageComponent} from "./odds.page.component";
import {LoginComponent} from "./login/login.component";
import {TicketService} from "../services/ticket.service";
import {UserService} from "../services/user.service";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {OddsComponent} from "./odds/odds.component";

@Component({
    selector: 'app',
    template: `
        <router-outlet></router-outlet>
    `,
    directives: [ROUTER_DIRECTIVES],
    providers: [TicketService, UserService],
    precompile: [OddsPageComponent, OddsComponent, LoginComponent, DashboardComponent]
})
export class AppComponent {

    constructor() {}
}