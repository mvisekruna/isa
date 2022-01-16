import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddPromotionRequest } from '../model/add-promotion-request';
import { Adventure } from '../model/adventure';
import { AdventurePromotionUserRequest } from '../model/adventure-promotion-user-request';
import { Promotion } from '../model/promotion';
import { PromotionAdventureUser } from '../model/promotion-adventure-user';

@Injectable({
  providedIn: 'root'
})
export class PromotionServiceService {

  promotionAdventureUserUrl: string;

  constructor(private http: HttpClient) {
    this.promotionAdventureUserUrl = "http://localhost:8080/promotionadventureuser"
  }

  //PROMOTION ADVENTURE*************************************************************************
  public findByIdPromotionAdventure(promotionId: any): Observable<Promotion> {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get<Promotion>(`localhost:8080/promotion/getOne/${promotionId}`, { headers });
  }

  public addPromotionToAdventure(addPromotionRequest: AddPromotionRequest): Observable<any> {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.post<Observable<any>>('http://localhost:8080/promotion/addpromotion/toadventure', addPromotionRequest, { headers });

  }

  public loadAllAdventurePromotions(): Observable<Promotion[]> {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get<Promotion[]>('http://localhost:8080/promotion/getalladventurepromotions', { headers });
  }
  //************************************************************************************************ 


  //PROMOTION ADVENTURE USER ***********************************************************************
  
  public findByIdPromotionAdventureUser(id: any): Observable<PromotionAdventureUser> {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get<PromotionAdventureUser>(`${this.promotionAdventureUserUrl}/${id}`, { headers });
  }

  public findAllPromotionAdventureUser(): Observable<PromotionAdventureUser[]> {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get<PromotionAdventureUser[]>(`${this.promotionAdventureUserUrl}/all`, { headers });
  }

  public findAllSubscribed(): Observable<Adventure[]> {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get<Adventure[]>(`${this.promotionAdventureUserUrl}/allsubscribed`, { headers });
  }
  
  public subscribeToPromotions(adventurePromotionUserRequest: AdventurePromotionUserRequest) {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.post(`${this.promotionAdventureUserUrl}/subscribe`, adventurePromotionUserRequest, { headers });
  }

  public delete(id: any): void {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    this.http.post(`${this.promotionAdventureUserUrl}/delete/${id}`, { headers });
  }

  public findByAdventureId(adventureId: any): Observable<PromotionAdventureUser>{
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get<PromotionAdventureUser>(`${this.promotionAdventureUserUrl}/adventure/${adventureId}`, { headers });
  }

  public unsubscribeFromAdventure(adventureId: any): any {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.post<any>(`${this.promotionAdventureUserUrl}/unsubscribe/${adventureId}`, { headers });
  }

  //************************************************************************************************ */
}
