import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {Router, ROUTER_DIRECTIVES} from "@angular/router";

@Component({
    moduleId: module.id,
    selector: 'navigation',
    directives: [ROUTER_DIRECTIVES],
    styleUrls: ['nav.component.css'],
    template: `
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" [routerLink]="['', 'odds']">Polyglot</a>
            </div>

            <ul class="nav navbar-nav navbar-right">
                <li class="navbar-text username">{{user}}</li>
                <li class="dropdown close">
                    <a aria-expanded="true" aria-haspopup="true" class="navbar-link dropdown-toggle" data-toggle="dropdown">
                        <img class="gravatar" src="app/components/navigation/gravatar.png" alt="{{user}}" />
                        <span class="sr-only">User Settings</span>
                    </a>
                    <ul class="dropdown-menu">
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
    </nav>
`
})
export class NavigationComponent implements OnInit {

    user: string;

    constructor(private userService: UserService, private router: Router) {
    }

    ngOnInit() {
        this.user = localStorage.getItem('user');
    }

    onLogout() {
        this.userService.removeUser();
        this.router.navigate(['login']);
    }
}