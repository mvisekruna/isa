import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Boat } from '../model/boat';
import { BoatRequest } from '../model/boat-request';

@Injectable({
  providedIn: 'root'
})
export class BoatServiceService {

  private boatUrl: string;
  constructor(private http: HttpClient) { 
    this.boatUrl = 'http://localhost:8080/boat';
  }

  public loadAll(): Observable<Boat[]> {
    return this.http.get<Boat[]>(`${this.boatUrl}/all`)
  }

  public getOne(id): Observable<Boat> {
    return this.http.get<Boat>(`${this.boatUrl}/one/`.concat(id))
  }

  public save(boatRequest: BoatRequest) {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.post('http://localhost:8080/boat/save', boatRequest, { headers });
  }
}
