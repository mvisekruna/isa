import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { PromotionVacationHomeUser } from 'src/app/model/promotion-vacation-home-user';
import { VacationHome } from 'src/app/model/vacation-home';
import { VacationHomeReservationRequest } from 'src/app/model/vacation-home-reservation-request';
import { PromotionVacationHomeServiceService } from 'src/app/service/promotion-vacation-home-service.service';
import { ReservationServiceService } from 'src/app/service/reservation-service.service';
import { UserServiceService } from 'src/app/service/user-service.service';
import { VacationHomeServiceService } from 'src/app/service/vacation-home-service.service';

@Component({
  selector: 'app-vacation-home-list',
  templateUrl: './vacation-home-list.component.html',
  styleUrls: ['./vacation-home-list.component.css']
})
export class VacationHomeListComponent implements OnInit {

  dtOptions: DataTables.Settings = {};
  terms: any;
  vacationHomes : VacationHome[] = [];
  vacationHomesSubsribed : VacationHome[] = [];
  title: string;
  dtTrigger: Subject<any> = new Subject<any>();
  dtTrigger2: Subject<any> = new Subject<any>();
  promotionVacationHomeUser: PromotionVacationHomeUser = new PromotionVacationHomeUser;
  isUser: boolean = false;
  role: any;
  isFree: boolean = false;
  vacationHomeReservationRequest: VacationHomeReservationRequest = new VacationHomeReservationRequest();

  constructor(private vacationHomeService: VacationHomeServiceService, 
    private router: Router, 
    private promotionVacationHomeServiceService: PromotionVacationHomeServiceService,
    private userService: UserServiceService,
    private reservationService: ReservationServiceService) {
      this.title = 'Vacation home list'
   }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      lengthMenu : [5, 10, 25],
      processing: true
    };

    this.role = localStorage.getItem('ROLES');
    if (this.role === 'ROLE_USER') {
      this.isUser = true;
    }

    this.vacationHomeService.loadAll().subscribe(data => {
      console.log(data);
      this.vacationHomes = data;
      this.dtTrigger.next();
    });

    if (this.role === 'ROLE_USER') {
      this.userService.findMySubscribedVacationHomes().subscribe(data => {
        this.vacationHomesSubsribed = data;
        this.dtTrigger2.next();
      });
    }
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
    this.dtTrigger2.unsubscribe();

  }

  details(id) {
    this.router.navigate(['/vacationhome',id]);  
  }

  chooseVacationHome(vacationHomeReservationRequest){ //kad dobijem slobodne avanture da izabere jednu od tih
    this.isUser == true;
    this.reservationService.chooseVacationHome(vacationHomeReservationRequest).subscribe(data => {
      console.log(data);
    })
  }

  searchForFreeVacationHomes() {
    //dodati isFree da bi se pojavila kolona za rezervaciju 
    this.isFree = true;
    this.reservationService.getFreeVacationHomes(this.vacationHomeReservationRequest).subscribe( data => {
      this.vacationHomes = data;
    });
  }

  cancelSubscription(id) {
    this.userService.cancelMyVacationHomeSubscription(id).subscribe(data => {
      console.log(data);
    });
    window.location.reload();
  }
}
