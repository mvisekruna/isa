import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { VacationHome } from '../model/vacation-home';
import { VacationHomeRequest } from '../model/vacation-home-request';

@Injectable({
  providedIn: 'root'
})
export class VacationHomeServiceService {

  private vacationHomeUrl: string;
  constructor(private http: HttpClient) {
    this.vacationHomeUrl = 'http://localhost:8080/vacationhome';
  }

  public loadAll(): Observable<VacationHome[]> {
    return this.http.get<VacationHome[]>(`${this.vacationHomeUrl}/all`);
  }

  public getOne(id): Observable<VacationHome> {
    return this.http.get<VacationHome>(`${this.vacationHomeUrl}/one/`.concat(id))
  }

  public save(vacationHomeRequest: VacationHomeRequest) {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.post('http://localhost:8080/vacationhome/save', vacationHomeRequest, { headers });
  }
}
