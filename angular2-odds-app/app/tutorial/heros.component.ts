import {Component, OnInit, Inject} from "@angular/core";
import {HeroService} from "./hero.service";
import {Hero} from "./hero";
import {HeroDetailComponent} from "./hero-detail.component";
import {Router} from "@angular/router";

@Component({
    selector: 'my-heroes',
    template: `
        <my-hero-detail [hero]="selectedHero"></my-hero-detail>

        <h2>My Heroes</h2>
        <ul class="heroes">
            <li *ngFor="let hero of heroes; let odd=odd; let even=even" 
                [ngClass]="{odd: odd, even: even}"
                [class.selected]="hero === selectedHero"
                (click)="gotoDetails(hero)">
                <span class="badge">{{hero.id}}</span> {{hero.name}}
            </li>
        </ul>
    `,
    directives: [HeroDetailComponent],
    styleUrls: ["app/tutorial/heroes.css"]
})
export class HeroesComponent implements OnInit {

    selectedHero:Hero;
    heroes:Hero[];

    constructor(@Inject(Router) private router: Router,
                @Inject(HeroService) private heroService:HeroService) { }

    onSelect(hero:Hero) {
        this.selectedHero = hero;
    }

    getHeroes() {
        this.heroService.getHeroes().then(heroes => this.heroes = heroes);
    }

    ngOnInit() {
        this.getHeroes();
    }

    gotoDetails(hero: Hero) {
        this.router.navigate(['/detail', hero.id]);
    }
}