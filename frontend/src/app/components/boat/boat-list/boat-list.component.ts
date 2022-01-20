import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { Boat } from 'src/app/model/boat';
import { PromotionBoatUser } from 'src/app/model/promotion-boat-user';
import { BoatServiceService } from 'src/app/service/boat-service.service';
import { PromotionBoatServiceService } from 'src/app/service/promotion-boat-service.service';

@Component({
  selector: 'app-boat-list',
  templateUrl: './boat-list.component.html',
  styleUrls: ['./boat-list.component.css']
})
export class BoatListComponent implements OnInit {

  dtOptions: DataTables.Settings = {};
  terms: any;
  boats: Boat[] = [];
  title: string;
  dtTrigger1: Subject<any> = new Subject<any>();
  dtTrigger2: Subject<any> = new Subject<any>();
  boatsSubscribed: Boat[] = [];
  promotionBoatUser: PromotionBoatUser = new PromotionBoatUser;
  isUser: boolean = false;
  role: any;


  constructor(private boatService: BoatServiceService, private router: Router, private promotionBoatServiceService: PromotionBoatServiceService) {
    this.title = 'Boat list';
  }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      lengthMenu: [5, 10, 25],
      processing: true
    };
    this.boatService.loadAll().subscribe(data => {
      console.log(data);
      this.boats = data;
      this.dtTrigger1.next();
    });

    this.role = localStorage.getItem('ROLES');
    if (this.role === 'ROLE_USER') {
      this.isUser = true;
    }

    if (this.role === 'ROLE_USER') {
      this.promotionBoatServiceService.findAllSubscribed().subscribe(data => {
      this.boatsSubscribed = data;
      this.dtTrigger2.next();
    })
  }
  }

  ngOnDestroy(): void {
    this.dtTrigger1.unsubscribe();
    this.dtTrigger2.unsubscribe();
  }

  details(id) {
    this.router.navigate(['/boat',id]);  
  }

  unsubscribe(boatId) {
    this.promotionBoatServiceService.unsubscribeFromBoat(boatId).subscribe(data => {
      console.log(data + ' unsubscribee');
    });
  }
}
