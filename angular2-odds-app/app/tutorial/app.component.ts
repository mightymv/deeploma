import {Component} from "@angular/core";
import {ROUTER_DIRECTIVES} from "@angular/router";
import {DashboardComponent} from "./dashboard.component";
import {HeroesComponent} from "./heros.component";
import {HeroDetailsComponent} from "./hero-details.component";
import {HeroService} from "./hero.service";

@Component({
    selector: 'my-app',
    template: `
        <h2>{{title}}</h2>
        <nav>
            <a [routerLink]="['/heroes']" routerLinkActive="active">Heroes</a>
            <a [routerLink]="['/dashboard']" routerLinkActive="active">Dash</a>
         </nav>
        <router-outlet></router-outlet>
    `,
    directives: [ROUTER_DIRECTIVES],
    providers: [HeroService],
    precompile: [HeroesComponent, DashboardComponent, HeroDetailsComponent],
    styleUrls: ["app/tutorial/app.component.css"]
})
export class AppComponent {

    title = "My Angular 2 App";
}
