import {Component, OnInit, Inject} from "@angular/core";
import {Hero} from "./hero";
import {HeroService} from "./hero.service";

@Component({
    selector: 'my-dashboard',
    template: `
         <h3>My Dashboard</h3>
         <ul class="heroes">
            <li *ngFor="let hero of heroes; let odd=odd; let even=even" 
                [ngClass]="{odd: odd, even: even}">
                <span class="badge">{{hero.id}}</span> {{hero.name}}
            </li>
        </ul>
    `,
    styleUrls: ["app/tutorial/dashboard.component.css"]

})
export class DashboardComponent implements OnInit {

    heroes: Hero[];

    constructor(@Inject(HeroService) private heroService: HeroService) { }

    getHeroes() {
        this.heroService.getHeroes().then(heroes => this.heroes = heroes.slice(1, 5));
    }

    ngOnInit() {
        this.getHeroes();
    }

    gotoDetails() {
        // TODO
    }
}