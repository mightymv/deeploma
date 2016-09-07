
import {LoginRequest, LoginResponse, RegistrationRequest} from "../dto/login";
export class Logger {

    public static logLoginRequest(requst: LoginRequest): void {
        console.log("Login request [username:" + requst.username +
            ", password:" + requst.password + "]");
    }

    public static logLoginResponse(response: LoginResponse): void {
        console.log("Login SUCCESS :)");
        console.log("Login response [id: "+ response.id +
            ", token: "+ response.token +
            ", name: " + response.name +
            ", surname: " + response.surname + "]");
    }

    public static logRegistrationRequest(request: RegistrationRequest): void {
        console.log("Registration request [name: " + request.name +
            ", surname: " + request.surname + "" +
            ", username:" + request.username +
            ", password:" + request.password + "]");
    }
}