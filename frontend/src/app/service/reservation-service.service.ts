import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AdventureReservationRequest } from '../model/adventure-reservation-request';
import { BoatReservationRequest } from '../model/boat-reservation-request';
import { CancelReservationRequest } from '../model/cancel-reservation-request';
import { ReservationHistoryResponse } from '../model/reservation-history-response';
import { UserEmailRequest } from '../model/user-email-request';
import { VacationHomeReservationRequest } from '../model/vacation-home-reservation-request';

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
    return this.http.post(`${this.reservationUrl}/get/freeadventures`, adventureReservationRequest, { headers });
  }

  public getFreeBoats(boatReservationRequest: BoatReservationRequest): any {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.post(`${this.reservationUrl}/get/freeboats`, boatReservationRequest, { headers });
  }
  
  public getFreeVacationHomes(vacationHomeReservationRequest: VacationHomeReservationRequest): any {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.post(`${this.reservationUrl}/get/freevhs`, vacationHomeReservationRequest, { headers });
  }

  public chooseAdventure(adventureReservationRequest: AdventureReservationRequest): any {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.post(`${this.reservationUrl}/choose/adventure`, adventureReservationRequest, { headers });
  }

  public chooseBoat(boatReservationRequest: BoatReservationRequest): any {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.post(`${this.reservationUrl}/choose/boat`, boatReservationRequest, { headers });
  }

  public chooseVacationHome(vacationHomeReservationRequest: VacationHomeReservationRequest): any {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.post(`${this.reservationUrl}/choose/vh`, vacationHomeReservationRequest, { headers });
  }
}