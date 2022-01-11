import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { VacationHome } from '../model/vacation-home';

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
}
