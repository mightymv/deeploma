import {bootstrap} from "@angular/platform-browser-dynamic";
import {AppComponent} from "./components/app.component";
import {APP_ROUTER_PROVIDERS} from "./routes/app.routes";
import {HTTP_PROVIDERS} from "@angular/http";

bootstrap(AppComponent, [
    APP_ROUTER_PROVIDERS,
    HTTP_PROVIDERS
    // {provide: XHRBackend, useClass: InMemoryBackendService}, // in-mem server
    // {provide: SEED_DATA, useClass: InMemoryDataService}      // in-mem server data
])
    .catch(err => console.error(err));
