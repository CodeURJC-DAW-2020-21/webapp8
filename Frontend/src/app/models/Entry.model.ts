import { UserModel } from "./User.model";

export class EntryModel {
    idEntry: number;
    title: String;
    description: String;
    //registrationDate: LocalDateTime;
    user: UserModel;
}