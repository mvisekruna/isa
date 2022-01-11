import { Component, OnInit } from '@angular/core';
import { AuthSingupInfo } from 'src/app/model/auth-singup-info';
import { AuthServiceService } from 'src/app/service/auth-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgModule } from '@angular/core';


@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {

  authSignUpInfo: AuthSingupInfo;
  title: string;
  registerForm: any = {};
  buttonName: string = 'Register now';
  div1: boolean = false;

  constructor(
    private authService: AuthServiceService) {
    this.title = 'Register';
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
    if (this.authSignUpInfo.password != this.authSignUpInfo.password_confirm) {
      alert("Please insert password again!");
    } else {
      alert('You will recieve verification mail. Please check Your mail!');

      if (this.authSignUpInfo.password.match(this.authSignUpInfo.password_confirm)) {
        this.authService.register(this.authSignUpInfo).subscribe(data => {
          console.log(data);
        });
      }
    }
  }
}
