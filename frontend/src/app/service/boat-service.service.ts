import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Boat } from '../model/boat';

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
}
