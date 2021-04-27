import { Byte } from "@angular/compiler/src/util";

export class UserModel {
    idUser: number;
    encodedPassword: string;
    name: string;
    surname: string;
    firstname: string;
    email: string;
    image?: any;
    roles: string[];
    registrationDate: Date;
}
