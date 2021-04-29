import { Byte } from "@angular/compiler/src/util";
import { CryptocurrencyModel } from "./Cryptocurrency.model";

export class UserModel {
    idUser?: number;
    encodedPassword?: string;
    name: string;
    surname: string;
    firstname: string;
    email: string;
    image?: any;
    roles: string[];
    registrationDate: Date;
    criptocurrencies: CryptocurrencyModel[];
}
