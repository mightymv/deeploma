import {Component, Input} from "@angular/core";
import {Hero} from "./hero";

@Component({
    "selector": 'my-hero-detail',
    "template": `
		<h2>My-Hero-Detail</h2>
		<div *ngIf="hero">
			<div class="contaner">
				<h2>{{hero.name}} details!</h2>
				<div><span>id: </span><span>{{hero.id}}</span></div>
				<div>
						<span>name: </span>
						<input [(ngModel)]="hero.name" placeholder="name of the hero">
				</div>
			</div>
		</div>
	`,
    styleUrls: ["app/tutorial/ticket_viewer.css"],
})

export class HeroDetailComponent {

    @Input()
    hero:Hero;
}