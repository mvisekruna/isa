import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PromotionVacationHomeUser } from 'src/app/model/promotion-vacation-home-user';
import { User } from 'src/app/model/user';
import { VacationHome } from 'src/app/model/vacation-home';
import { VacationHomePromotionUserRequest } from 'src/app/model/vacation-home-promotion-user';
import { UserServiceService } from 'src/app/service/user-service.service';
import { VacationHomeServiceService } from 'src/app/service/vacation-home-service.service';

@Component({
  selector: 'app-vacation-home-details',
  templateUrl: './vacation-home-details.component.html',
  styleUrls: ['./vacation-home-details.component.css']
})
export class VacationHomeDetailsComponent implements OnInit {
  title: string;
  vacationHome: VacationHome;
  vacationHomePromotionUserRequest: VacationHomePromotionUserRequest = new VacationHomePromotionUserRequest();
  promotionUser: User = new User;
  email:any;
  role:any;
  isUser:any;
  promotionVacationHomeUser: PromotionVacationHomeUser = new PromotionVacationHomeUser();

  constructor(private vacationHomeService: VacationHomeServiceService, 
    private route: ActivatedRoute,
    private userService: UserServiceService) { }

  ngOnInit(): void {
    this.title = 'Vacation home details';

    this.role = localStorage.getItem('ROLES');
    if (this.role === 'ROLE_USER') {
      this.isUser = true;
    }

    this.vacationHomeService.getOne(this.route.snapshot.paramMap.get('id')).subscribe(data => {
      console.log(data);
      this.vacationHome = data;
    });
  }

  subscribeToPromotions(vacationHomeId) {
    this.userService.subscribeToVacationHomePromotions(vacationHomeId).subscribe(data => {
      if(data===null){
        alert('Already subscribed!');
      }
      });
    }
}
