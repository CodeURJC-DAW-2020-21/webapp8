import { UserModel } from './User.model';

export class CommentModel {
    idComment: number;
    descriptionComment: string;
    user: UserModel[];
}