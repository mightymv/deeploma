import {Injectable} from "@angular/core";
import {Hero} from "./hero";
import {HEROES} from "./mock-heroes";
import {Http} from "@angular/http";
import 'rxjs/add/operator/toPromise';

@Injectable()
export class HeroService {

    private heroesUrl = 'app/heroes'; // URL to web api

    constructor(private http:Http) {
    }


    getHeroes():Promise<Hero[]> {
        // return Promise.resolve(HEROES);
        return this.http
            .get(this.heroesUrl)
            .toPromise()
            .then(response => response.json().data)
            .catch(this.handleError);
    }

    private handleError(error:any) {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }

    getHeroesSlowly() {
        return new Promise<Hero[]>(resolve =>
            setTimeout(() => resolve(HEROES), 2000)
        );
    }

    getHero(id:number) {
        return this.getHeroes()
            .then(heroes => heroes.find(hero => hero.id === id));
    }
}