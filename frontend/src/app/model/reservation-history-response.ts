import { AdventureReservationHistoryResponse } from "./adventure-reservation-history-response";
import { BoatReservationHistoryResponse } from "./boat-reservation-history-response";
import { VacationHomeReservationHistoryResponse } from "./vacation-home-reservation-history-response";

export class ReservationHistoryResponse {
    vacationHomeReservations: VacationHomeReservationHistoryResponse[];
    boatReservations: BoatReservationHistoryResponse[];
    adventureReservations: AdventureReservationHistoryResponse[];
}