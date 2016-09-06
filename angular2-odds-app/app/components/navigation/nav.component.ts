import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'navigation',
    styleUrls: ['app/components/navigation/nav.component.css'],
    template: `
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Polyglot</a>
            </div>

            <ul class="nav navbar-nav navbar-right">
                <li class="navbar-text username">aleksa@gmail.com</li>
                <li class="dropdown close">
                    <a aria-expanded="true" aria-haspopup="true" class="navbar-link dropdown-toggle" data-toggle="dropdown">
                        <img class="gravatar" src="app/components/navigation/gravatar.png" alt="aleksa888@gmail.com" />
                        <span class="sr-only">User Settings</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="/dashboard">Dashboard</a>
                        </li>
                        <li>
                            <a href="../login">Log out</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
`
})
export class NavigationComponent implements OnInit {
    constructor() {
    }

    ngOnInit() {
    }
}