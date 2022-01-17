import { Component, OnInit } from '@angular/core';
import { AuthSingupInfo } from 'src/app/model/auth-singup-info';
import { AuthServiceService } from 'src/app/service/auth-service.service';
import { UserServiceService } from 'src/app/service/user-service.service';

@Component({
  selector: 'app-registration-for-others-form',
  templateUrl: './registration-for-others-form.component.html',
  styleUrls: ['./registration-for-others-form.component.css']
})
export class RegistrationForOthersFormComponent implements OnInit {

  authSignUpInfo: AuthSingupInfo;
  title: string;
  registerForOthersForm: any = {};
  buttonName: string = 'Register';
  div1: boolean = false;

  constructor(
    private authService: AuthServiceService, private userService: UserServiceService) {
    this.title = 'Registration for owners';
    this.authSignUpInfo = new AuthSingupInfo();
  }

  ngOnInit(): void {
  }

  div1Function() {
    this.div1 = !this.div1;
    if (this.div1 == true) {
      this.buttonName = 'Not now';
    } else { this.buttonName = 'Register now'; }
  }

  onSubmit() {
    console.log(this.authSignUpInfo);
    if(this.userService.findByEmail(this.authSignUpInfo.email)!=null) {
      alert("That email already exists");
    }
    if (this.authSignUpInfo.password != this.authSignUpInfo.password_confirm) {
      alert("Please insert password again!");
    }

      if (this.authSignUpInfo.password.match(this.authSignUpInfo.password_confirm)) {
        this.authService.registrationForOthers(this.authSignUpInfo).subscribe(data => {
          console.log(data);
        });
      }
    }

}
