import { THIS_EXPR, ThrowStmt } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  info: any;
  isUser: boolean = false;
  isAdmin = false;
  isTutor = false;
  isVacationHomeOwner = false;
  isBoatOwner = false;
  title = 'Fishing system';
  constructor() {

  }

  ngOnInit(): void {
    if (localStorage.getItem("TOKEN") == null) {
      localStorage.setItem("ROLES", "UNAUTHENTICATED");
    }
    this.info = {
      token: localStorage.getItem("TOKEN"),
      email: localStorage.getItem("USERNAME"),
      roles: localStorage.getItem("ROLES")
    };
    console.log(this.info.token);
    console.log(this.info.roles);
    if (this.info.roles == "ROLE_USER") {
      this.isUser = true;
    } else if (this.info.roles == "ROLE_ADMIN") {
      this.isAdmin = true;
    } else if (this.info.roles == "ROLE_TUTOR") {
      this.isTutor = true;
    } else if (this.info.roles == "ROLE_VACATION_HOME_OWNER") {
      this.isVacationHomeOwner = true;
    } else if (this.info.roles == "ROLE_BOAT_OWNER") {
      this.isBoatOwner = true;
    }
  }
  logout() {
    localStorage.clear();
    localStorage.setItem("ROLES", "UNAUTHENTICATED");
    window.location.reload();
    this.isUser = false;
    this.isAdmin = false;
  }
}
