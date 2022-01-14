import { Component, OnInit } from '@angular/core';
import { AuthSingupInfo } from 'src/app/model/auth-singup-info';

@Component({
  selector: 'app-home-page-admin',
  templateUrl: './home-page-admin.component.html',
  styleUrls: ['./home-page-admin.component.css']
})
export class HomePageAdminComponent implements OnInit {
  
  isMainAdmin: boolean = false;
  email: any;

  constructor() {
  }

  ngOnInit(): void {
    this.email = localStorage.getItem('USERNAME');
    if(this.email === 'admin@example.com') {
      this.isMainAdmin = true;
    }
  }
}
