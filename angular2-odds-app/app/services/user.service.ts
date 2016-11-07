import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import {Logger} from "../utils/LoggerUtils";
import {LoginResponse, LoginRequest, RegistrationRequest, User} from "../dto/login";
import {Router} from "@angular/router";
import { CookieService } from 'angular2-cookie/services/cookies.service';

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

       return this.http.post("http://192.168.182.198:8080/login2", JSON.stringify(loginRequest), {headers: headers})
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

    saveUserToLocalStorage(loginResponse: LoginResponse): void {
        localStorage.setItem('id', loginResponse.id.toString());
        localStorage.setItem('token', loginResponse.token);
        localStorage.setItem('user', loginResponse.name + ' ' + loginResponse.surname);
    }

    removeUserFromLocalStorage(): void {
        localStorage.removeItem('id');
        localStorage.removeItem('token');
        localStorage.removeItem('user');
    }

    getUserFromLocalStorage(): User {
        return this.getUser();
    }
    // getUserFromLocalStorage(): User {
    //     return new User(localStorage.getItem('id'),
    //         localStorage.getItem('token'), localStorage.getItem('user'));
    // }

    onRegister(registrationRequst: RegistrationRequest) {

        if(registrationRequst == null) {
            return;
        }

        let headers = new Headers();
        headers.append("Content-Type", "application/json");

        Logger.logRegistrationRequest(registrationRequst);

        return this.http.put("http://192.168.182.198:8080/user", JSON.stringify(registrationRequst), {headers: headers})
            .map(res => res.text())
            .toPromise();
    }
}