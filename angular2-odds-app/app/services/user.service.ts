import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import {Logger} from "../utils/LoggerUtils";
import {LoginResponse, LoginRequest, RegistrationRequest} from "../dto/login";
import {Router} from "@angular/router";

@Injectable()
export class UserService {

    constructor(private http: Http, private router: Router) { }

    onLogin(loginRequest: LoginRequest): void {

        if(loginRequest == null) {
            return;
        }

        let headers = new Headers();
        headers.append('X-Auth-Username', loginRequest.username);
        headers.append('X-Auth-Password', loginRequest.password);

        Logger.logLoginRequest(loginRequest);

        this.http.post("http://local.angular2odds.com:8080/login2", JSON.stringify(loginRequest), {headers: headers})
            .map(res => res.json())
            .subscribe(
                response => {
                    let loginResponse: LoginResponse = response;
                    Logger.logLoginResponse(response);
                    this.saveUserToLocalStorage(loginResponse);
                    this.router.navigate(['odds']);
                },
                err => {
                    console.log("Login failed !!! " + err);
                },
                () => console.log('Login completed.')
            );
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

    onRegister(registrationRequst: RegistrationRequest) {

        if(registrationRequst == null) {
            return;
        }

        let headers = new Headers();
        headers.append("Content-Type", "application/json");

        Logger.logRegistrationRequest(registrationRequst);

        this.http.put("http://local.angular2odds.com:8080/user", JSON.stringify(registrationRequst), {headers: headers})
            .map(res => res.text())
            .subscribe(
                success => {
                    console.log("Registration SUCCESS :) " + success);
                    this.router.navigate(['login']);
                },
                err => console.log("Registration failed !!! " + err),
                () => console.log('Registration completed.')
            );
    }
}