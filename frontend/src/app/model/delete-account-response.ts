import { User } from "./user";

export class DeleteAccountResponse {

    id: string;

    user: User;

    reason: string;

    confirm: boolean;

    requestProcessed: boolean;

}
