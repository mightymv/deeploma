import {Injectable} from "@angular/core";
import {Router, CanActivate} from "@angular/router";
import {UserService} from "./user.service";

@Injectable()
export class AuthService implements CanActivate {

    constructor(private router: Router, private userService: UserService) { }

    isAuthorized(): boolean {
        return Boolean(this.userService.getUser().token).valueOf();
    }

    canActivate(): boolean {

        const isAuth = this.isAuthorized();

        if(!isAuth) {
            this.router.navigate(['/login']);
        }

        return isAuth.valueOf();
    }
}