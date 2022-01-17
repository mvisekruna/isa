import { Component, OnInit } from '@angular/core';
import { UserUpdateRequest } from 'src/app/model/user-update-request';
import { UserServiceService } from 'src/app/service/user-service.service';
import { AuthServiceService } from 'src/app/service/auth-service.service';
import { AuthLoginInfo } from 'src/app/model/auth-login-info';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  authlogin: AuthLoginInfo;
  title: string;
  info: any;
  userInfo = {
    email: null,
    firstName: null,
    lastName: null,
    address: null,
    city: null,
    state: null,
    phoneNumber: null
  };
  reason: string;
  changeInfo: boolean = false;
  changePass: boolean = false;
  deleteAccountForm: boolean = false;
  userUpdateRequest: UserUpdateRequest;
  passRequest: any = {};
  isUser: boolean = false;
  isDr: boolean = false;
  showDeleteAccountButton: boolean = true;
  constructor(private _location: Location, private userService: UserServiceService, private routher: Router, private authService: AuthServiceService) {
    this.userUpdateRequest = new UserUpdateRequest();
    this.authlogin = new AuthLoginInfo("", "");
  }

  ngOnInit(): void {
    if (localStorage.getItem('ROLES') == 'ROLE_USER') {
      this.isUser = true;
    } else if (localStorage.getItem('ROLES') != 'ROLE_ADMIN') {
      this.isDr = true;
  }
    this.title = "My profile";
    this.info = {
      email: localStorage.getItem("USERNAME"),
      token: localStorage.getItem("TOKEN")
    };
    console.log(this.info.token);
    this.userService.getMyInfo(this.info.email)
      .subscribe(data => {
        console.log(data);
        this.userInfo = {
          email: data.email,
          firstName: data.firstName,
          lastName: data.lastName,
          address: data.address,
          city: data.city,
          state: data.state,
          phoneNumber: data.phoneNumber
        };
        console.log(this.userInfo);
      }
      );
    if (this.isUser) {
      console.log('nesto')
    }
  }
  changeInfoFun() {
    this.changeInfo = true;
  }

  cancelFun() {
    this.changeInfo = false;
  }

  saveChangeFun() {
    this.userUpdateRequest.firstName = (<HTMLInputElement>document.getElementById("firstName")).value;
    this.userUpdateRequest.lastName = (<HTMLInputElement>document.getElementById("lastName")).value;
    this.userUpdateRequest.address = (<HTMLInputElement>document.getElementById("address")).value;
    this.userUpdateRequest.city = (<HTMLInputElement>document.getElementById("city")).value;
    this.userUpdateRequest.state = (<HTMLInputElement>document.getElementById("state")).value;
    this.userUpdateRequest.phoneNumber = (<HTMLInputElement>document.getElementById("phoneNumber")).value;
    console.log(this.userUpdateRequest);
    console.log(this.info.token)
    this.userService.updateUserInfo(this.userUpdateRequest, this.info.token)
      .subscribe(data => {
        console.log(data);
        this.userInfo = {
          email: data.email,
          firstName: data.firstName,
          lastName: data.lastName,
          address: data.address,
          city: data.city,
          state: data.state,
          phoneNumber: data.phoneNumber
        };
      }
      );
    this.changeInfo = false;
    // window.location.reload();

  }
  changePassFun() {
    this.changePass = true;
  }
  saveChangePassFun() {
    this.passRequest.oldPassword = (<HTMLInputElement>document.getElementById("oldpass")).value;
    this.passRequest.newPassword = (<HTMLInputElement>document.getElementById("newpass")).value;
    console.log(this.passRequest);
    this.userService.updateUserPassword(this.passRequest, this.info.token)
      .subscribe(data => {
        console.log(data);
        this.userInfo = { // ako ovde vraca usera
          email: data.email,
          firstName: data.firstName,
          lastName: data.lastName,
          address: data.address,
          city: data.city,
          state: data.state,
          phoneNumber: data.phoneNumber
        }
        // probam da resim bag da posle promene pas nece da menja ostale podatke
        this.authlogin.email = data.email;
        this.authlogin.password = this.passRequest.newPassword;
        localStorage.clear();
        console.log(this.authlogin);
        this.authService.attemptAuth(this.authlogin)
          .subscribe(data => {
            console.log(data);
            localStorage.setItem("TOKEN", data.accessToken);

            this.userService.getMyInfo(this.authlogin.email)
              .subscribe(data => {
                console.log(data);
                localStorage.setItem("USERNAME", data.email);
                localStorage.setItem("ROLES", JSON.stringify(data.roles).split("\"")[3]); //7 za drugi autoriti
                console.log(localStorage);
              });
          }
          );
      }
      );
    this.changePass = false;
    console.log(localStorage);
    localStorage.clear();
    window.location.reload();
  }
  cancelPassFun() {
    this.changePass = false;
  }

  deleteAccountFun(){
    this.reason = (<HTMLInputElement>document.getElementById("reason")).value;
    this.userService.sendRequestForDeleteAccount(this.reason).subscribe(data =>{
      console.log(data);
    });
    // localStorage.clear();
    // localStorage.setItem("ROLES", "UNAUTHENTICATED");
  //  window.location.reload();
  //  this.isUser = false;
     this.deleteAccountForm = false;
     this.showDeleteAccountButton = true;
     alert("Request for deleting account is sent!");


  }

  showDeleteAccountForm() {
   this.deleteAccountForm = true;
   this.showDeleteAccountButton = false;
  }


}
