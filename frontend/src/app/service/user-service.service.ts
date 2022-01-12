import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ApiServiceService } from './api-service.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserUpdateRequest } from '../model/user-update-request';
@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  currentUser: User;
  private userUrl: string;
  constructor(private apiService: ApiServiceService,
    private http: HttpClient) {
    this.userUrl = 'http://localhost:8080/api';
  }

  public updateUserInfo(userUpdateRequest: UserUpdateRequest, t: string): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.post(`${this.userUrl}/updateuser`, userUpdateRequest, { headers });
  }

  public updateUserPassword(passwordRequest: any, t: string): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.post(`${this.userUrl}/changepassword`, passwordRequest, { headers });
  }

  public loadAllTutors(): Observable<User[]> {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get<User[]>(`${this.userUrl}/tutors`, { headers });
  }

  public getMyInfo(email: string): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.userUrl}/{email}`, email, { headers });
  }

  public addUser(body: any): Observable<User> {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.post<User>(`${this.userUrl}/adduser`, body, { headers });
  }

  public deleteAccount() {
    const t = localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
    return this.http.get(`${this.userUrl}/deleteaccount`, { headers })
  }


}
