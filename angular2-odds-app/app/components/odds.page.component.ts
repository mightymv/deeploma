import {Component} from '@angular/core';
import {NavigationComponent} from "./navigation/nav.component";

@Component({
    selector: 'odds-page-app',
    templateUrl: 'app/components/odds.page.component.html',
    styleUrls: ["app/components/main-page.css"],
    directives: [NavigationComponent]
})

export class OddsPageComponent {
}