import { Adventure } from "./adventure";
import { Boat } from "./boat";
import { VacationHome } from "./vacation-home";

export class Promotion {

    id: string;
    startPromo: string;
    endPromo: string;
    description: string;
    adventurePromotion: Adventure;
    boatPromotion: Boat;
    vacationHomePromotion: VacationHome;
    numberOfPromotions: number;
}
