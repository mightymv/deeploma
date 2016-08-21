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
var DashboardComponent = (function () {
    function DashboardComponent(heroService) {
        this.heroService = heroService;
    }
    DashboardComponent.prototype.getHeroes = function () {
        var _this = this;
        this.heroService.getHeroes().then(function (heroes) { return _this.heroes = heroes.slice(1, 5); });
    };
    DashboardComponent.prototype.ngOnInit = function () {
        this.getHeroes();
    };
    DashboardComponent.prototype.gotoDetails = function () {
        // TODO
    };
    DashboardComponent = __decorate([
        core_1.Component({
            selector: 'my-dashboard',
            template: "\n         <h3>My Dashboard</h3>\n         <ul class=\"heroes\">\n            <li *ngFor=\"let hero of heroes; let odd=odd; let even=even\" \n                [ngClass]=\"{odd: odd, even: even}\">\n                <span class=\"badge\">{{hero.id}}</span> {{hero.name}}\n            </li>\n        </ul>\n    ",
            styleUrls: ["app/tutorial/dashboard.component.css"]
        }),
        __param(0, core_1.Inject(hero_service_1.HeroService)), 
        __metadata('design:paramtypes', [hero_service_1.HeroService])
    ], DashboardComponent);
    return DashboardComponent;
}());
exports.DashboardComponent = DashboardComponent;
//# sourceMappingURL=dashboard.component.js.map