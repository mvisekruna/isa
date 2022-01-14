import { Component, OnInit } from '@angular/core';
import { AuthSingupInfo } from 'src/app/model/auth-singup-info';
import { AuthServiceService } from 'src/app/service/auth-service.service';

@Component({
  selector: 'app-add-admin',
  templateUrl: './add-admin.component.html',
  styleUrls: ['./add-admin.component.css']
})
export class AddAdminComponent implements OnInit {

  authSignUpInfo: AuthSingupInfo;
  title: string;
  registerForm: any = {};
  
  constructor(private authService: AuthServiceService) {
    this.title = 'Add new admin';
    this.authSignUpInfo = new AuthSingupInfo();
   }

  ngOnInit(): void {
  }

  onSubmit() {
    console.log(this.authSignUpInfo);
    if (this.authSignUpInfo.password != this.authSignUpInfo.password_confirm) {
      alert("Please insert password again!");
    } else {

      if (this.authSignUpInfo.password.match(this.authSignUpInfo.password_confirm)) {
       this.authService.addNewAdmin(this.authSignUpInfo).subscribe(data => {
         console.log(data);
         alert('Admin successfully added!');
       });
      }
    }
  }

}
