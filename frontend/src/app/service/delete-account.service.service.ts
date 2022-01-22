import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DeleteAccountResponse } from '../model/delete-account-response';

@Injectable({
    providedIn: 'root'
  })
  export class DeleteAccounService {
  
    private deleteAccount: string;
    constructor(private http: HttpClient) { 
      this.deleteAccount = 'http://localhost:8080/deleteaccount';
    }
  
    public loadAll(): Observable<DeleteAccountResponse[]> {
      const t = localStorage.getItem("TOKEN");
      const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
      return this.http.get<DeleteAccountResponse[]>(`${this.deleteAccount}/all`, {headers})
    }

    public deleteApproved(id: string, reason: string): any {
      const t = localStorage.getItem("TOKEN");
      const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
      return this.http.post(`${this.deleteAccount}/deleteapproved/${id}`, reason, {headers})
    }

    public deleteDenied(id: string, reason: string):  any {
      const t = localStorage.getItem("TOKEN");
      const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).set("Authorization", "Bearer " + t);
      return this.http.post(`${this.deleteAccount}/deletedenied/${id}`, reason, {headers})
    }
    

}