import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReservationHistoryResponse } from '../model/reservation-history-response';
import { UserEmailRequest } from '../model/user-email-request';

@Injectable({
  providedIn: 'root'
})
export class ReservationServiceService {

  private reservationUrl: string;
  constructor(private http: HttpClient) {
    this.reservationUrl = 'http://localhost:8080/reservation';
  }

  public getReservationHistory(userEmailRequest: UserEmailRequest): Observable<ReservationHistoryResponse> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<ReservationHistoryResponse>(`${this.reservationUrl}`, userEmailRequest, { headers });
  }
}