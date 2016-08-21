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
var core_1 = require('@angular/core');
var hero_service_1 = require("./hero.service");
var router_1 = require("@angular/router");
var HeroDetailsComponent = (function () {
    function HeroDetailsComponent(heroService, route) {
        this.heroService = heroService;
        this.route = route;
    }
    HeroDetailsComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.sub = this.route.params.subscribe(function (params) {
            /* +string -> converting string into number */
            var id = +params['id'];
            _this.heroService.getHero(id).then(function (hero) { return _this.hero = hero; });
        });
    };
    HeroDetailsComponent.prototype.ngOnDestroy = function () {
        this.sub.unsubscribe();
    };
    HeroDetailsComponent.prototype.goBack = function () {
        window.history.back();
    };
    HeroDetailsComponent = __decorate([
        core_1.Component({
            selector: 'hero-details',
            template: "\n        <h2>My-Hero-Detail</h2>\n        <div *ngIf=\"hero\">\n            <div class=\"contaner\">\n                    <h2>{{hero.name}} details!</h2>\n                    <div>\n                        <span>id: </span><span>{{hero.id}}</span>\n                    </div>\n                    <div>\n                        <span>name: </span>\n                        <input [(ngModel)]=\"hero.name\" placeholder=\"name of the hero\">\n                    </div>\n                    <button (click)=\"goBack()\">Back</button>\n            </div>\n        </div>\n    "
        }),
        __param(0, core_1.Inject(hero_service_1.HeroService)),
        __param(1, core_1.Inject(router_1.ActivatedRoute)), 
        __metadata('design:paramtypes', [hero_service_1.HeroService, router_1.ActivatedRoute])
    ], HeroDetailsComponent);
    return HeroDetailsComponent;
}());
exports.HeroDetailsComponent = HeroDetailsComponent;
//# sourceMappingURL=hero-details.component.js.map