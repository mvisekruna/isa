export class VacationHomeReservationHistoryResponse {
    id: number;
    startDate: string;
    endDate: string;
    price: DoubleRange;
    vacationHomeName: string;
    vacationHomeLocation: string;
    duration: number;
    status: string;
    canCancel: boolean;
}
