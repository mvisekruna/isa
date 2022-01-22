import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { Boat } from 'src/app/model/boat';
import { BoatReservationRequest } from 'src/app/model/boat-reservation-request';
import { PromotionBoatUser } from 'src/app/model/promotion-boat-user';
import { BoatServiceService } from 'src/app/service/boat-service.service';
import { PromotionBoatServiceService } from 'src/app/service/promotion-boat-service.service';
import { ReservationServiceService } from 'src/app/service/reservation-service.service';
import { UserServiceService } from 'src/app/service/user-service.service';

@Component({
  selector: 'app-boat-list',
  templateUrl: './boat-list.component.html',
  styleUrls: ['./boat-list.component.css']
})
export class BoatListComponent implements OnInit {

  dtOptions: DataTables.Settings = {};
  terms: any;
  boats: Boat[] = [];
  boatsSubscribed: Boat[] = [];
  promotionBoatUser: PromotionBoatUser = new PromotionBoatUser();
  title: string;
  dtTrigger1: Subject<any> = new Subject<any>();
  dtTrigger2: Subject<any> = new Subject<any>();
  role: any;
  isUser: boolean = false;
  isFree: boolean = false;
  boatReservationRequest: BoatReservationRequest = new BoatReservationRequest();


  constructor(private boatService: BoatServiceService, 
    private router: Router, 
    private promotionBoatServiceService: PromotionBoatServiceService,
    private userService: UserServiceService,
    private reservationService: ReservationServiceService) {
    this.title = 'Boat list';
  }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      lengthMenu: [5, 10, 25],
      processing: true
    };

    this.role = localStorage.getItem('ROLES');
    if (this.role === 'ROLE_USER') {
      this.isUser = true;
    }

    this.boatService.loadAll().subscribe(data => {
      console.log(data);
      this.boats = data;
      this.dtTrigger1.next();
    });

 

    if (this.role === 'ROLE_USER') {
      this.userService.findMySubscribedBoats().subscribe(data => {
      this.boatsSubscribed = data;
      this.dtTrigger2.next();
    });
    }
  }

  ngOnDestroy(): void {
    this.dtTrigger1.unsubscribe();
    this.dtTrigger2.unsubscribe();
  }

  details(id) {
    this.router.navigate(['/boat',id]);  
  }

  chooseTheBoat(boatReservationRequest){ 
    this.isUser == true;
    this.reservationService.chooseBoat(boatReservationRequest).subscribe(data => {
      console.log(data);
    })
  }

  searchForFreeBoats() {
    //dodati isFree da bi se pojavila kolona za rezervaciju 
    this.isFree = true;
    this.reservationService.getFreeBoats(this.boatReservationRequest).subscribe( data => {
      this.boats = data;
    });
  }

  cancelSubscription(id) {
    this.userService.cancelMyBoatSubscription(id).subscribe(data => {
      console.log(data);
    });
    window.location.reload();
  }
}
