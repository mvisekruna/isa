import { Component, OnInit } from '@angular/core';
import { AuthSingupInfo } from 'src/app/model/auth-singup-info';
import { User } from 'src/app/model/user';
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
  user: User = new User;

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
    this.userService.findByEmail(this.authSignUpInfo.email).subscribe(data=>{
      this.user = data;
    });

    if (this.authSignUpInfo.password != this.authSignUpInfo.password_confirm) {
      alert("Please insert password again!");
    } else if(this.user!=null) {
      alert('Username already exists!')
    } else {
      alert('Please wait for confirmation!');
    }
      if (this.authSignUpInfo.password.match(this.authSignUpInfo.password_confirm)) {
        this.authService.registrationForOthers(this.authSignUpInfo).subscribe(data => {
          console.log(data);
        });
      }
    }

}
