import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AddPromotionRequest } from "../model/add-promotion-request";
import { Promotion } from "../model/promotion";
import { PromotionVacationHomeUser } from "../model/promotion-vacation-home-user";
import { VacationHome } from "../model/vacation-home";

@Injectable({
    providedIn: 'root'
  })
  export class PromotionVacationHomeServiceService {

    promotionVacationHomeUserUrl: string;

    constructor(private http: HttpClient) {
      this.promotionVacationHomeUserUrl = "http://localhost:8080/promotionvacationhomeuser"
    }
  
  //PROMOTION VACATION HOME*************************************************************************
  public findByIdPromotionVacationHome(promotionId: any): Observable<Promotion> {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get<Promotion>(`localhost:8080/promotion/vacation/home/getOne/${promotionId}`, { headers });
  }

  public addPromotionToVacationHome(addPromotionRequest: AddPromotionRequest): Observable<any> {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.post<Observable<any>>('http://localhost:8080/promotion/vacation/home/addpromotion/tovacationhome', addPromotionRequest, { headers });

  }

  public loadAllVacationHomePromotions(): Observable<Promotion[]> {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get<Promotion[]>('http://localhost:8080/promotion/vacation/home/getallvactionhomepromotions', { headers });
  }
  //************************************************************************************************ 


  //PROMOTION VACATION HOME USER ***********************************************************************
  
  public findByIdPromotionVacationHomeUser(id: any): Observable<PromotionVacationHomeUser> {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get<PromotionVacationHomeUser>(`${this.promotionVacationHomeUserUrl}/${id}`, { headers });
  }

  public findAllPromotionVacationHomeUser(): Observable<PromotionVacationHomeUser[]> {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get<PromotionVacationHomeUser[]>(`${this.promotionVacationHomeUserUrl}/all`, { headers });
  }

  public findAllWithVacationHomeId(vacationHomeId: any): any {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get(`${this.promotionVacationHomeUserUrl}/all/vacationhomes/${vacationHomeId}`, { headers });
  }

  public findAllWithPromotionId(promotionId: any): any {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get(`${this.promotionVacationHomeUserUrl}/all/promotions/${promotionId}`, { headers });
  }

  public delete(id: any): void {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    this.http.post(`${this.promotionVacationHomeUserUrl}/delete/${id}`, { headers });
  }

  public findByVacationHomeId(vacationHomeId: any): any{
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get(`${this.promotionVacationHomeUserUrl}/vacation/home/${vacationHomeId}`, { headers });
  }

  public findAllVacationHomePromotionsIChose(): any {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get(`${this.promotionVacationHomeUserUrl}/allpromotions/ichose`, { headers });
  }

  public chooseThePromotion(promotionId: any): any {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.post(`${this.promotionVacationHomeUserUrl}/choose/${promotionId}`, promotionId, { headers });
  }

  public cancelThePromotion(vacationHomeId: any): any {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.post(`${this.promotionVacationHomeUserUrl}/cancel/${vacationHomeId}`, vacationHomeId, { headers });
  }



  //************************************************************************************************ */

  }