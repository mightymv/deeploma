import {Component} from "@angular/core";
import {Http, Headers, RequestOptions} from "@angular/http";


@Component({
    selector: 'login',
    templateUrl: 'app/components/login/login.component.html',
    styleUrls: ['app/components/login/login.component.css']
})
export class LoginComponent {

    name: string = "name";
    surname: string = "surname";
    username: string = "username";
    password: string;

    // md5.createHash(User.senha)

    constructor(private http: Http) {}

    onLogin() {

        let body = {
            "username": this.username,
            "password": this.password,
        };

        let headers = new Headers();
        headers.append('X-Auth-Username', this.username);
        headers.append('X-Auth-Password', this.password);

        this.printRequest();

        this.http.post("http://local.angular2odds.com:8080/login2", JSON.stringify(body), {headers: headers})
            .map(res => res.json())
            .subscribe(
                success => {
                    console.log("USPESNO logovanje: jwt - " + success.token);
                },
                err => {this.logError(err); console.log(err.json())},
                () => console.log('Login completed')
            );
    }

    onRegister() {

        let body = {
            "name": this.name,
            "surname": this.surname,
            "username": this.username,
            "password": this.password,
        };
        let headers = this.getHeader();

        this.printRequest();

        this.http.put("http://192.168.182.198:8080/user", JSON.stringify(body), {headers: headers})
            .map(res => res.json())
            .subscribe(
                success => {
                    console.log("USPESNO : " + success);
                },
                err => this.logError(err),
                () => console.log('Registration completed')
            );
    }

    getHeader() {
        let headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append('Access-Control-Allow-Origin', '*');
        return headers;
    }

    printRequest() {
        console.log("Request: name=" + this.name +
            ", surname=" + this.surname + ", username=" + this.username +
            ", password=" + this.password);
    }

    logError(err) {
        console.log("Registracija neuspesna !!! " + err);
    }
}