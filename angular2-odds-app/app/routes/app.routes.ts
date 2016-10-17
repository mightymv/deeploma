import {provideRouter, RouterConfig} from "@angular/router";
import {LoginComponent} from "../components/login/login.component";
import {OddsPageComponent} from "../components/odds.page.component";
import {DashboardComponent} from "../components/dashboard/dashboard.component";
import {OddsComponent} from "../components/odds/odds.component";

export const routes: RouterConfig = [
    {
        path: '',
        component: OddsPageComponent,
        children: [
            {path: '', component: OddsComponent},
            {path: 'odds', component: OddsComponent},
            {path: 'dashboard', component: DashboardComponent}
        ]
    },
    {
        path: 'login',
        component: LoginComponent
    }
];

export const APP_ROUTER_PROVIDERS = [
    provideRouter(routes)
];