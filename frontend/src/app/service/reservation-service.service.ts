import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AdventureReservationRequest } from '../model/adventure-reservation-request';
import { CancelReservationRequest } from '../model/cancel-reservation-request';
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
    return this.http.post<ReservationHistoryResponse>(`${this.reservationUrl}/all`, userEmailRequest, { headers });
  }
  public cancelBoatReservation(cancelReservationRequest: CancelReservationRequest): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.reservationUrl}/cancel/boat`, cancelReservationRequest, { headers });
  }

  public cancelAdventureReservation(cancelReservationRequest: CancelReservationRequest): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.reservationUrl}/cancel/adventure`, cancelReservationRequest, { headers });
  }

  public cancelVacationHomeReservation(cancelReservationRequest: CancelReservationRequest): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.reservationUrl}/cancel/vacationHome`,cancelReservationRequest, { headers });
  }

  public getFreeAdventures(adventureReservationRequest: AdventureReservationRequest): any {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get(`${this.reservationUrl}/get/freeadventures`, { headers });
  }

  


}