import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { AdventurePromotionUserRequest } from 'src/app/model/adventure-promotion-user-request';
import { Promotion } from 'src/app/model/promotion';
import { PromotionBoatServiceService } from 'src/app/service/promotion-boat-service.service';
import { PromotionServiceService } from 'src/app/service/promotion-service.service';
import { PromotionVacationHomeServiceService } from 'src/app/service/promotion-vacation-home-service.service';

@Component({
  selector: 'app-promotion-list',
  templateUrl: './promotion-list.component.html',
  styleUrls: ['./promotion-list.component.css']
})
export class PromotionListComponent implements OnInit {

  dtOptions: DataTables.Settings = {};
  terms: any;
  adventurePromotions: Promotion[] = [];
  boatPromotions: Promotion[] = [];
  vacationHomePromotions: Promotion[] = [];

  title1: string;
  title2: string;
  title3: string;

  dtTrigger1: Subject<any> = new Subject<any>();
  dtTrigger2: Subject<any> = new Subject<any>();
  dtTrigger3: Subject<any> = new Subject<any>();

  isUser: boolean = false;
  isTutor: boolean = false;
  isBoatOwner: boolean = false;
  isVacationHomeOwner: boolean = false;
  role: any;
  adventurePromotionUserRequest: AdventurePromotionUserRequest = new AdventurePromotionUserRequest;

  constructor(private promotionService: PromotionServiceService, 
    private promotionBoatServiceService: PromotionBoatServiceService,
    private promotionVacationHomeService: PromotionVacationHomeServiceService) {
    this.title1 = 'Adventure promotions';
    this.title2 = 'Boat promotions';
    this.title3 = 'Vacation home promotions'
  }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      lengthMenu: [5, 10, 25],
      processing: true
    };
    this.role = localStorage.getItem('ROLES');
    if(this.role === 'ROLE_USER') {
      this.isUser = true;
    } else if(this.role === 'ROLE_BOAT_OWNER') {
      this.isBoatOwner = true;
    } else if(this.role === 'ROLE_TUTOR') {
      this.isTutor = true;
    } else {
      this.isVacationHomeOwner = true;
    }
    this.promotionService.loadAllAdventurePromotions().subscribe(data => {
      this.adventurePromotions = data;
      console.log(this.adventurePromotions);
      this.dtTrigger1.next();

    });

    this.promotionBoatServiceService.loadAllBoatPromotions().subscribe(data => {
      this.boatPromotions = data;
      console.log(this.boatPromotions);
      this.dtTrigger2.next();

    });

    this.promotionVacationHomeService.loadAllVacationHomePromotions().subscribe(data => {
      this.vacationHomePromotions = data;
      this.dtTrigger3.next();

    });
  }

  chooseAdventurePromotion(id:any): void {
    this.promotionService.choosePromotion(id).subscribe( data => {
      if(data === null) {
        alert('Already chose this one!');
      }
    });
  }

  chooseBoatPromotion(id:any): void {
    this.promotionBoatServiceService.chooseThePromotion(id).subscribe( data => {
      if(data === null) {
        alert('Already subscribed!');
      }
    });
  }

  chooseVacationHomePromotion(id:any): void {
    this.promotionVacationHomeService.chooseThePromotion(id).subscribe( data => {
      if(data === null) {
        alert('Already chose this one!');
      }
    });
  }

  ngOnDestroy(): void {
    this.dtTrigger1.unsubscribe();
    this.dtTrigger2.unsubscribe();
    this.dtTrigger3.unsubscribe();
  }

}
