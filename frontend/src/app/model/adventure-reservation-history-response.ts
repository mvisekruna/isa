export class AdventureReservationHistoryResponse {
    id: number;
    startDate: string;
    endDate: string;
    adventureName: string;
    adventureAddress: string;
    numberOfPeople: number;
    price: DoubleRange;
    duration: number;
    status: string;
    canCancel: boolean;
}