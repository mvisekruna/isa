import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { PromotionVacationHomeUser } from 'src/app/model/promotion-vacation-home-user';
import { VacationHome } from 'src/app/model/vacation-home';
import { PromotionVacationHomeServiceService } from 'src/app/service/promotion-vacation-home-service.service';
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


  constructor(private vacationHomeService: VacationHomeServiceService, private router: Router, private promotionVacationHomeServiceService: PromotionVacationHomeServiceService) {
    this.title = 'Vacation home list'
   }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      lengthMenu : [5, 10, 25],
      processing: true
    };
    this.vacationHomeService.loadAll().subscribe(data => {
      console.log(data);
      this.vacationHomes = data;
      this.dtTrigger.next();
    });

    this.role = localStorage.getItem('ROLES');
    if (this.role === 'ROLE_USER') {
      this.isUser = true;
    }

    if (this.role === 'ROLE_USER') {
      this.promotionVacationHomeServiceService.findAllSubscribed().subscribe(data => {
      this.vacationHomesSubsribed = data;
      console.log(this.vacationHomesSubsribed);
      this.dtTrigger2.next();
    })
  }
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
    this.dtTrigger2.unsubscribe();

  }

  details(id) {
    this.router.navigate(['/vacationhome',id]);  
  }

  unsubscribe(vacationHomeId) {
    this.promotionVacationHomeServiceService.unsubscribeFromVacationHome(vacationHomeId).subscribe(data => {
      console.log(data + ' unsubscribee');
    });
  }
}
