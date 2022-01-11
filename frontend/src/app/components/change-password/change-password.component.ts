import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthLoginInfo } from 'src/app/model/auth-login-info';
import { AuthServiceService } from 'src/app/service/auth-service.service';
import { UserServiceService } from 'src/app/service/user-service.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  passwordRequest: any = {};
  info: any;
  userInfo: any;
  authlogin: AuthLoginInfo;
  constructor(private userService: UserServiceService,
    private router: Router,
    private authService: AuthServiceService) {
    this.authlogin = new AuthLoginInfo("", "");
  }

  ngOnInit(): void {
    this.info = {
      email: localStorage.getItem("USERNAME"),
      t: localStorage.getItem("TOKEN")
    };
  }

  saveChangePassword() {
    this.passwordRequest.oldPassword = (<HTMLInputElement>document.getElementById("oldpassword")).value;
    this.passwordRequest.newPassword = (<HTMLInputElement>document.getElementById("newpassword")).value;

    this.userService.updateUserPassword(this.passwordRequest, this.info.t)
      .subscribe(data => {
        this.userInfo = {
          email: data.email,
          firstName: data.firstName,
          lastName: data.lastName,
          address: data.address,
          city: data.city,
          state: data.state,
          phoneNumber: data.phoneNumber
        }

        this.authlogin.email = data.email;
        this.authlogin.password = this.passwordRequest.newPassword;
        localStorage.clear();
        this.authService.attemptAuth(this.authlogin)
          .subscribe(data => {
            localStorage.setItem("TOKEN", data.accessToken);

            this.userService.getMyInfo(this.authlogin.email)
              .subscribe(data => {
                console.log(data);
                localStorage.setItem("USERNAME", data.email);
                localStorage.setItem("ROLES", JSON.stringify(data.roles).split("\"")[3]);
                console.log(localStorage);
              });
          }
          );
      }
      );
    console.log(localStorage);
    localStorage.clear();
    this.router.navigate(["/homepage"]);
  }

}
