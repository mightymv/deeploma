import {Component, OnInit, OnDestroy, Inject} from '@angular/core';
import {Hero} from "./hero";
import {HeroService} from "./hero.service";
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'hero-details',
    template: `
        <h2>My-Hero-Detail</h2>
        <div *ngIf="hero">
            <div class="contaner">
                    <h2>{{hero.name}} details!</h2>
                    <div>
                        <span>id: </span><span>{{hero.id}}</span>
                    </div>
                    <div>
                        <span>name: </span>
                        <input [(ngModel)]="hero.name" placeholder="name of the hero">
                    </div>
                    <button (click)="goBack()">Back</button>
            </div>
        </div>
    `
})
export class HeroDetailsComponent implements OnInit, OnDestroy {

    hero:Hero;
    private sub:any;

    constructor(@Inject(HeroService) private heroService:HeroService,
                @Inject(ActivatedRoute) private route:ActivatedRoute) {
    }

    ngOnInit() {
        this.sub = this.route.params.subscribe(params => {

            /* +string -> converting string into number */
            let id = +params['id'];
            this.heroService.getHero(id).then(hero => this.hero = hero);
        });
    }

    ngOnDestroy() {
        this.sub.unsubscribe();
    }

    goBack() {
        window.history.back();
    }
}