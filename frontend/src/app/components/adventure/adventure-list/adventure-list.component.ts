import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { Adventure } from 'src/app/model/adventure';
import { AdventureReservationRequest } from 'src/app/model/adventure-reservation-request';
import { PromotionAdventureUser } from 'src/app/model/promotion-adventure-user';
import { AdventureServiceService } from 'src/app/service/adventure-service.service';
import { PromotionServiceService } from 'src/app/service/promotion-service.service';
import { ReservationServiceService } from 'src/app/service/reservation-service.service';
import { UserServiceService } from 'src/app/service/user-service.service';

@Component({
  selector: 'app-adventure-list',
  templateUrl: './adventure-list.component.html',
  styleUrls: ['./adventure-list.component.css']
})
export class AdventureListComponent implements OnInit {

  dtOptions1: DataTables.Settings = {};
  terms: any;
  adventures: Adventure[] = [];
  adventuresSubscribed: Adventure[] = [];
  promotionAdventureUser: PromotionAdventureUser = new PromotionAdventureUser();
  title: string;
  dtTrigger1: Subject<any> = new Subject<any>();
  dtTrigger2: Subject<any> = new Subject<any>();
  role: any;
  isUser: boolean = false;
  isFree: boolean = false;
  adventureReservationRequest: AdventureReservationRequest = new AdventureReservationRequest();

  constructor(private adventureService: AdventureServiceService, 
    private router: Router, 
    private promotionService: PromotionServiceService,
    private userService: UserServiceService,
    private reservationService: ReservationServiceService) {
    this.title = 'Adventure list';
  }

  ngOnInit(): void {
    this.dtOptions1 = {
      pagingType: 'full_numbers',
      pageLength: 5,
      lengthMenu: [5, 10, 25],
      processing: true
    };

    this.role = localStorage.getItem('ROLES');
    if (this.role === 'ROLE_USER') {
      this.isUser = true;
    }

    this.adventureService.loadAll().subscribe(data => {
      console.log(data);
      this.adventures = data;
      this.dtTrigger1.next();
    });

    if (this.role === 'ROLE_USER') {
      this.userService.findMySubscribedAdventures().subscribe(data => {
        this.adventuresSubscribed = data;
        this.dtTrigger2.next();
      });
    }
  }

  ngOnDestroy(): void {
    this.dtTrigger1.unsubscribe();
    this.dtTrigger2.unsubscribe();
  }

  details(id) {
    this.router.navigate(['/adventure', id]);
  }

  chooseTheAdventure(adventureReservationRequest){ //kad dobijem slobodne avanture da izabere jednu od tih
    this.isUser == true;
    //dodati na dugme
  }

  searchForFreeAdventures() {
    //dodati isFree da bi se pojavila kolona za rezervaciju 
    this.isFree = true;
    this.reservationService.getFreeAdventures(this.adventureReservationRequest).subscribe( data => {
      this.adventures = data;
    });
  }

  cancelSubscription(id) {
    this.userService.cancelMyAdventureSubscription(id).subscribe(data => {
      console.log(data);
    });
    window.location.reload();
  }
}
