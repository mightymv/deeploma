import {Component} from '@angular/core';
import {ROUTER_DIRECTIVES} from "@angular/router";

@Component({
    selector: 'odds-page-app',
    templateUrl: 'app/components/odds.page.component.html',
    styleUrls: ["app/components/main-page.css"],
    directives: [ROUTER_DIRECTIVES],
})

export class OddsPageComponent {
}