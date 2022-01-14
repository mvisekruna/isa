import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Adventure } from '../model/adventure';

@Injectable({
  providedIn: 'root'
})
export class AdventureServiceService {

  private adventureUrl: string;
  constructor(private http: HttpClient) {
    this.adventureUrl = 'http://localhost:8080/adventure';
  }

  public loadAll(): Observable<Adventure[]> {
    return this.http.get<Adventure[]>(`${this.adventureUrl}/all`);
  }

  public getOne(id): Observable<Adventure> {
    return this.http.get<Adventure>(`${this.adventureUrl}/one/`.concat(id))
  }
}
