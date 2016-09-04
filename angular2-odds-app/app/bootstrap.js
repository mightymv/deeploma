"use strict";
var platform_browser_dynamic_1 = require("@angular/platform-browser-dynamic");
var app_component_1 = require("./components/app.component");
var app_routes_1 = require("./routes/app.routes");
var http_1 = require("@angular/http");
var index_1 = require("angular2-in-memory-web-api/index");
var InMemoryDataService_1 = require("./tutorial/InMemoryDataService");
platform_browser_dynamic_1.bootstrap(app_component_1.AppComponent, [
    app_routes_1.APP_ROUTER_PROVIDERS,
    http_1.HTTP_PROVIDERS,
    { provide: http_1.XHRBackend, useClass: index_1.InMemoryBackendService },
    { provide: index_1.SEED_DATA, useClass: InMemoryDataService_1.InMemoryDataService } // in-mem server data
])
    .catch(function (err) { return console.error(err); });
//# sourceMappingURL=bootstrap.js.map