import { Component } from '@angular/core';
import {ROUTER_DIRECTIVES} from "@angular/router";
import {OddsPageComponent} from "./odds.page.component";
import {LoginComponent} from "./login/login.component";

@Component({
    selector: 'app',
    template: `
        <router-outlet></router-outlet>
    `,
    directives: [ROUTER_DIRECTIVES],
    precompile: [OddsPageComponent, LoginComponent]
})
export class AppComponent {}