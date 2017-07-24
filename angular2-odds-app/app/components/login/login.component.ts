import {Component} from "@angular/core";
import {LoginRequest, RegistrationRequest, LoginResponse} from "../../dto/login";
import {UserService} from "../../services/user.service";
import {Logger} from "../../utils/LoggerUtils";
import {Router} from "@angular/router";
import "rxjs/add/operator/toPromise";

declare var jQuery:any;

@Component({
    moduleId: module.id,
    selector: 'login',
    templateUrl: 'login.component.html',
    styleUrls: ['login.component.css']
})
export class LoginComponent {

    name: string;
    surname: string;
    username: string;
    password: string;

    loginMessage: boolean = false;
    registerMessage: boolean = false;

    constructor(private userService: UserService, private router: Router) {
    }

    onLogin() {
        let loginRequest: LoginRequest = new LoginRequest(this.username, this.password);
        this.userService.onLogin(loginRequest)
            .then(
                response => {
                    let userData: LoginResponse = response;

                    Logger.logLoginResponse(userData);

                    this.userService.saveUser(userData);

                    this.router.navigate(['odds']);
                })
            .catch(err => {
                console.log("Login failed !!! " + err);
                this.loginMessage = true;
            });
    }

    onLoginErrorClick() {
        this.loginMessage = false;
    }

    enterEventHandler() {
        if(this.username !== undefined && this.password !== undefined) {
            this.onLogin();
        }
    }

    registrationEnterEventHandler() {
        if(this.name !== undefined && this.surname !== undefined && this.password !== undefined && this.password !== undefined) {
            this.onRegister();
        }
    }

    onRegister() {
        let registrationRequst: RegistrationRequest = new RegistrationRequest(this.name,
            this.surname, this.username, this.password);
        this.userService.onRegister(registrationRequst)
            .then(success => {
                console.log("Registration SUCCESS :) " + success);
                jQuery('.round-tabs.one').click();
                jQuery('.loginButton').focus();
            })
            .catch(err => {
                console.log("Registration failed !!! " + err);
                this.registerMessage = true;
            });
    }

    onRegisterErrorClick() {
        this.registerMessage = false;
    }
}