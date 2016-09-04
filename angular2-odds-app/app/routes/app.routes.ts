import {provideRouter, RouterConfig} from "@angular/router";
import {LoginComponent} from "../components/login/login.component";
import {OddsPageComponent} from "../components/odds.page.component";

export const routes: RouterConfig = [
    {path: 'login', component: LoginComponent},
    {path: 'odds', component: OddsPageComponent},
    {path: '', redirectTo: '/odds', pathMatch: 'full'}
];

export const APP_ROUTER_PROVIDERS = [
    provideRouter(routes)
];