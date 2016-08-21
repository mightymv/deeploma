import {bootstrap} from "@angular/platform-browser-dynamic";
import {AppComponent} from "./tutorial/app.component";
import {APP_ROUTER_PROVIDERS} from "./tutorial/app.routes";
import {HTTP_PROVIDERS, XHRBackend} from "@angular/http";
import {InMemoryBackendService, SEED_DATA} from "angular2-in-memory-web-api/index";
import {InMemoryDataService} from "./tutorial/InMemoryDataService";

bootstrap(AppComponent, [
    APP_ROUTER_PROVIDERS,
    HTTP_PROVIDERS,
    { provide: XHRBackend, useClass: InMemoryBackendService}, // in-mem server
    { provide: SEED_DATA, useClass: InMemoryDataService}      // in-mem server data
    ])
    .catch(err => console.error(err));
