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
var core_1 = require("@angular/core");
var router_1 = require("@angular/router");
var dashboard_component_1 = require("./dashboard.component");
var heros_component_1 = require("./heros.component");
var hero_details_component_1 = require("./hero-details.component");
var hero_service_1 = require("./hero.service");
var AppComponent = (function () {
    function AppComponent() {
        this.title = "My Angular 2 App";
    }
    AppComponent = __decorate([
        core_1.Component({
            selector: 'my-app',
            template: "\n        <h2>{{title}}</h2>\n        <nav>\n            <a [routerLink]=\"['/heroes']\" routerLinkActive=\"active\">Heroes</a>\n            <a [routerLink]=\"['/dashboard']\" routerLinkActive=\"active\">Dash</a>\n         </nav>\n        <router-outlet></router-outlet>\n    ",
            directives: [router_1.ROUTER_DIRECTIVES],
            providers: [hero_service_1.HeroService],
            precompile: [heros_component_1.HeroesComponent, dashboard_component_1.DashboardComponent, hero_details_component_1.HeroDetailsComponent],
            styleUrls: ["app/tutorial/app.component.css"]
        }), 
        __metadata('design:paramtypes', [])
    ], AppComponent);
    return AppComponent;
}());
exports.AppComponent = AppComponent;
//# sourceMappingURL=app.component.js.map