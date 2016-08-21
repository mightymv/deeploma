"use strict";
var router_1 = require("@angular/router");
var dashboard_component_1 = require("./dashboard.component");
var heros_component_1 = require("./heros.component");
var hero_details_component_1 = require("./hero-details.component");
exports.routes = [
    { path: 'dashboard', component: dashboard_component_1.DashboardComponent },
    { path: 'heroes', component: heros_component_1.HeroesComponent },
    { path: 'detail/:id', component: hero_details_component_1.HeroDetailsComponent },
    { path: '', redirectTo: '/heroes', pathMatch: 'full' }
];
exports.APP_ROUTER_PROVIDERS = [
    router_1.provideRouter(exports.routes)
];
//# sourceMappingURL=app.routes.js.map