import {Component, OnInit} from "@angular/core";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";

@Component({
    moduleId: module.id,
    selector: 'navigation',
    styleUrls: ['nav.component.css'],
    template: `
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" [routerLink]="['', 'odds']">Polyglot</a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-left">
                    <li><a *ngIf="isAuth" class="link" [routerLink]="['', 'standings']">Standings</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="navbar-text username" (click)="onLogin()">
                        <a [ngClass]="{'login': !isAuth }">{{user}}</a>
                    </li>
                    <li class="dropdown close">
                        <a aria-expanded="true" aria-haspopup="true" class="navbar-link dropdown-toggle" data-toggle="dropdown">
                            <img class="gravatar" src="app/components/navigation/gravatar.png" alt="{{user}}" />
                            <span class="sr-only">User Settings</span>
                        </a>
                        <ul class="dropdown-menu" *ngIf="isAuth">
                            <li>
                                <a [routerLink]="['', 'dashboard']">Dashboard</a>
                            </li>
                            <li>
                                <a [routerLink]="['', 'login']" (click)="onLogout()">Logout</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
`
})
export class NavigationComponent implements OnInit {

    user: string;
    isAuth: boolean = false;

    constructor(private userService: UserService,
                private router: Router,
                private authService: AuthService) {
    }

    ngOnInit() {
        this.user = this.userService.getUser().user;
        this.isAuth = this.authService.isAuthorized();
    }

    onLogin() {
        if(this.isAuth) {
            return;
        }
        this.router.navigate(['login']);
    }

    onLogout() {
        this.userService.removeUser();
        this.router.navigate(['login']);
    }
}