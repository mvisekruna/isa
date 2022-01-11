import { User } from "./user";

export class Adventure {
    id: string;
    adventureName: string;
    adventureAddress: string;
    adventureDescription: string;
    adventurePrice: string;
    adventureReview: string;
    adventureTutor: User;
}
