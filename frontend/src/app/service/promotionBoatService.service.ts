import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AddPromotionRequest } from "../model/add-promotion-request";
import { Boat } from "../model/boat";
import { BoatPromotionUserRequest } from "../model/boat-promotion-user-request";
import { Promotion } from "../model/promotion";
import { PromotionBoatUser } from "../model/promotion-boat-user";

@Injectable({
    providedIn: 'root'
  })
  export class PromotionBoatServiceService {

    promotionBoatUserUrl: string;

    constructor(private http: HttpClient) {
      this.promotionBoatUserUrl = "http://localhost:8080/promotionboatuser"
    }
  
  //PROMOTION BOAT*************************************************************************
  public findByIdPromotionBoat(promotionId: any): Observable<Promotion> {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get<Promotion>(`localhost:8080/promotion/getOne/${promotionId}`, { headers });
  }

  public addPromotionToBoat(addPromotionRequest: AddPromotionRequest): Observable<any> {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.post<Observable<any>>('http://localhost:8080/promotion/addpromotion/toboat', addPromotionRequest, { headers });

  }

  public loadAllBoatPromotions(): Observable<Promotion[]> {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get<Promotion[]>('http://localhost:8080/promotion/getallboatpromotions', { headers });
  }
  //************************************************************************************************ 


  //PROMOTION BOAT USER ***********************************************************************
  
  public findByIdPromotionBoatUser(id: any): Observable<PromotionBoatUser> {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get<PromotionBoatUser>(`${this.promotionBoatUserUrl}/${id}`, { headers });
  }

  public findAllPromotionBoatUser(): Observable<PromotionBoatUser[]> {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get<PromotionBoatUser[]>(`${this.promotionBoatUserUrl}/all`, { headers });
  }

  public findAllSubscribed(): Observable<Boat[]> {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get<Boat[]>(`${this.promotionBoatUserUrl}/allsubscribed`, { headers });
  }
  
  public subscribeToPromotions(boatPromotionUserRequest: BoatPromotionUserRequest) {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.post(`${this.promotionBoatUserUrl}/subscribe`, boatPromotionUserRequest, { headers });
  }

  public delete(id: any): void {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    this.http.post(`${this.promotionBoatUserUrl}/delete/${id}`, { headers });
  }

  public findByBoatId(boatId: any): Observable<PromotionBoatUser>{
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get<PromotionBoatUser>(`${this.promotionBoatUserUrl}/boat/${boatId}`, { headers });
  }

  public unsubscribeFromBoat(boatId: any): any {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.post<any>(`${this.promotionBoatUserUrl}/unsubscribe/${boatId}`, { headers });
  }

  //************************************************************************************************ */

  }