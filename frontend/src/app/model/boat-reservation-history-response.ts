export class BoatReservationHistoryResponse {
    id: number;
    startDate: string;
    endDate: string;
    boatName: string;
    boatLocation: string;
    price: DoubleRange;
    duration: number;
    status: string;
    canCancel: boolean;
}