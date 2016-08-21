import {provideRouter, RouterConfig} from "@angular/router";
import {DashboardComponent} from "./dashboard.component";
import {HeroesComponent} from "./heros.component";
import {HeroDetailsComponent} from "./hero-details.component";

export const routes:RouterConfig = [
    {path: 'dashboard', component: DashboardComponent},
    {path: 'heroes', component: HeroesComponent},
    {path: 'detail/:id', component: HeroDetailsComponent},
    {path: '', redirectTo: '/heroes', pathMatch: 'full'}
];

export const APP_ROUTER_PROVIDERS = [
    provideRouter(routes)
];