import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import {Logger} from "../utils/LoggerUtils";
import {LoginResponse, LoginRequest, RegistrationRequest, User} from "../dto/login";
import { CookieService } from 'angular2-cookie/services/cookies.service';
import {TopUser} from "../dto/standings";

@Injectable()
export class UserService {

    constructor(private http: Http,
                private _cookieService:CookieService) { }

    onLogin(loginRequest: LoginRequest): Promise<any> {

        if(loginRequest == null) {
            return;
        }

        let headers = new Headers();
        headers.append('X-Auth-Username', loginRequest.username);
        headers.append('X-Auth-Password', loginRequest.password);

        Logger.logLoginRequest(loginRequest);

       return this.http.post("http://192.168.182.198:8081/login2", JSON.stringify(loginRequest), {headers: headers})
            .map(res => res.json())
            .toPromise();
    }

    saveUser(loginResponse: LoginResponse): void {

        let expiresDate: Date = new Date();
        expiresDate.setHours(expiresDate.getHours() + 1);

        this._cookieService.putObject("user", new User(
            loginResponse.id,
            loginResponse.token,
            loginResponse.name + ' ' + loginResponse.surname), {"expires": expiresDate});
    }

    removeUser(): void {
        this._cookieService.remove("user");
    }

    getUser(): User {
        let user: User = <User>this._cookieService.getObject("user");
        return user === undefined ? new User(0, "", "Login") : user;
    }

    onRegister(registrationRequst: RegistrationRequest) {

        if(registrationRequst == null) {
            return;
        }

        let headers = new Headers();
        headers.append("Content-Type", "application/json");

        Logger.logRegistrationRequest(registrationRequst);

        return this.http.put("http://192.168.182.198:8081/user", JSON.stringify(registrationRequst), {headers: headers})
            .map(res => res.text())
            .toPromise();
    }

    standings() {

        let headers = new Headers();
        headers.append("Content-Type", "application/json");

        return this.http.get("http://192.168.182.198:8081/standings/2016-08-10", {headers: headers})
            .map(res => res.json())
            .map(
                (response: Array<any>) => {

                    let convertedResponse = new Array<TopUser>();
                    response.forEach(topUser => convertedResponse.push(new TopUser(topUser)));
                    return convertedResponse;
                })
            .toPromise();
    }
}