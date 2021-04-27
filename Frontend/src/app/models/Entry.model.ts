import { UserModel } from "./User.model";

export class EntryModel {
    idEntry: number;
    title: string;
    description: string;
    registrationDate: Date;
    user: UserModel;
}