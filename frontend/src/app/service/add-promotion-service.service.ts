import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddPromotionRequest } from '../model/add-promotion-request';
import { Promotion } from '../model/promotion';

@Injectable({
  providedIn: 'root'
})
export class AddPromotionServiceService {
  
  adventurePromotionUserUrl: string;

  constructor(private http: HttpClient) {
    this.adventurePromotionUserUrl = "http://localhost:8080/adventurepromouser"
   }

  public addPromotionToAdventure(addPromotionRequest: AddPromotionRequest): Observable<any> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post<Observable<any>>('http://localhost:8080/promotion/addpromotion/toadventure' , addPromotionRequest, {headers});
  
  }

  public loadAllAdventurePromotions(): Observable<Promotion[]> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<Promotion[]>('http://localhost:8080/promotion/getalladventurepromotions' , {headers});
  }

  public subscribeToPromotion(id: any): Observable<any> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get(`${this.adventurePromotionUserUrl}/subscribe/${id}`, { headers })

  }
}
