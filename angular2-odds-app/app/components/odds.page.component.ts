import {Component} from "@angular/core";
import {MessageModalComponent} from "./messagesModal/messageModal.component";
import {NavigationComponent} from "./navigation/nav.component";
import { ROUTER_DIRECTIVES } from '@angular/router'

@Component({
    moduleId: module.id,
    selector: 'odds-page-app',
    templateUrl: 'odds.page.component.html',
    styleUrls: ["odds.page.component.css"],
    directives: [
        NavigationComponent,
        ...ROUTER_DIRECTIVES
    ]
})

export class OddsPageComponent {

    constructor() {}
}