import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddPromotionRequest } from '../model/add-promotion-request';

@Injectable({
  providedIn: 'root'
})
export class AddPromotionServiceService {

  constructor(private http: HttpClient) { }

  public addPromotionToAdventure(addPromotionRequest: AddPromotionRequest): Observable<any> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post<Observable<any>>('http://localhost:8080/promotion/addpromotion/toadventure' , addPromotionRequest, {headers});
  
  }
}
