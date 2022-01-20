import { User } from "./user";
import { VacationHome } from "./vacation-home";

export class PromotionVacationHomeUser {

    id: string;

    vacationHome: VacationHome;

    promotionUser: User;

    isSubscribed: boolean;

}
