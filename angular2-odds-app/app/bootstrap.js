"use strict";
var platform_browser_dynamic_1 = require("@angular/platform-browser-dynamic");
var app_component_1 = require("./components/app.component");
var app_routes_1 = require("./routes/app.routes");
var http_1 = require("@angular/http");
var angular2_jwt_1 = require("angular2-jwt");
platform_browser_dynamic_1.bootstrap(app_component_1.AppComponent, [
    app_routes_1.APP_ROUTER_PROVIDERS,
    http_1.HTTP_PROVIDERS,
    { provide: angular2_jwt_1.AuthHttp, useClass: angular2_jwt_1.AuthHttp }
])
    .catch(function (err) { return console.error(err); });
//# sourceMappingURL=bootstrap.js.map