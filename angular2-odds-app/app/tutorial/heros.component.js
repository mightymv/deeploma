"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
var core_1 = require("@angular/core");
var hero_service_1 = require("./hero.service");
var hero_detail_component_1 = require("./hero-detail.component");
var router_1 = require("@angular/router");
var HeroesComponent = (function () {
    function HeroesComponent(router, heroService) {
        this.router = router;
        this.heroService = heroService;
    }
    HeroesComponent.prototype.onSelect = function (hero) {
        this.selectedHero = hero;
    };
    HeroesComponent.prototype.getHeroes = function () {
        var _this = this;
        this.heroService.getHeroes().then(function (heroes) { return _this.heroes = heroes; });
    };
    HeroesComponent.prototype.ngOnInit = function () {
        this.getHeroes();
    };
    HeroesComponent.prototype.gotoDetails = function (hero) {
        this.router.navigate(['/detail', hero.id]);
    };
    HeroesComponent = __decorate([
        core_1.Component({
            selector: 'my-heroes',
            template: "\n        <my-hero-detail [hero]=\"selectedHero\"></my-hero-detail>\n\n        <h2>My Heroes</h2>\n        <ul class=\"heroes\">\n            <li *ngFor=\"let hero of heroes; let odd=odd; let even=even\" \n                [ngClass]=\"{odd: odd, even: even}\"\n                [class.selected]=\"hero === selectedHero\"\n                (click)=\"gotoDetails(hero)\">\n                <span class=\"badge\">{{hero.id}}</span> {{hero.name}}\n            </li>\n        </ul>\n    ",
            directives: [hero_detail_component_1.HeroDetailComponent],
            styleUrls: ["app/tutorial/heroes.css"]
        }),
        __param(0, core_1.Inject(router_1.Router)),
        __param(1, core_1.Inject(hero_service_1.HeroService)), 
        __metadata('design:paramtypes', [router_1.Router, hero_service_1.HeroService])
    ], HeroesComponent);
    return HeroesComponent;
}());
exports.HeroesComponent = HeroesComponent;
//# sourceMappingURL=heros.component.js.map