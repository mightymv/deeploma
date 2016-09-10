import {Component} from "@angular/core";
import {LoginRequest, RegistrationRequest} from "../../dto/login";
import {UserService} from "../../services/user.service";


@Component({
    moduleId: module.id,
    selector: 'login',
    templateUrl: 'login.component.html',
    styleUrls: ['login.component.css']
})
export class LoginComponent {

    name: string ;
    surname: string;
    username: string;
    password: string;

    constructor(private userService: UserService) {}

    onLogin() {

        let loginRequest: LoginRequest = new LoginRequest(this.username, this.password);

        this.userService.onLogin(loginRequest);
    }

    onRegister() {

        let registrationRequst: RegistrationRequest = new RegistrationRequest(this.name,
            this.surname, this.username, this.password);

        this.userService.onRegister(registrationRequst);
    }
}