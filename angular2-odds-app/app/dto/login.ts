export class LoginRequest {

    constructor(public username: string,
                public password: string) {}
}

export class LoginResponse {

    constructor(public token: string,
                public name: string,
                public surname: string,
                public id: number) {
    }
}

export class RegistrationRequest {

    constructor(public name: string,
                public surname: string,
                public username: string,
                public password: string) {}
}

export class LocalStorageUser {
    constructor(public id: number,
                public token: string,
                public user: string) {}
}