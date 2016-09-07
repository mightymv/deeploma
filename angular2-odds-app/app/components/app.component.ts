import { Component } from '@angular/core';
import {ROUTER_DIRECTIVES} from "@angular/router";
import {OddsPageComponent} from "./odds.page.component";
import {LoginComponent} from "./login/login.component";
import {PayTicketService} from "../services/pay-ticket.service";
import {UserService} from "../services/user.service";

@Component({
    selector: 'app',
    template: `
        <router-outlet></router-outlet>
    `,
    directives: [ROUTER_DIRECTIVES],
    providers: [PayTicketService, UserService],
    precompile: [OddsPageComponent, LoginComponent]
})
export class AppComponent {}