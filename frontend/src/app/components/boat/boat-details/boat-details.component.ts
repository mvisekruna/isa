import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Boat } from 'src/app/model/boat';
import { BoatPromotionUserRequest } from 'src/app/model/boat-promotion-user-request';
import { PromotionBoatUser } from 'src/app/model/promotion-boat-user';
import { BoatServiceService } from 'src/app/service/boat-service.service';
import { UserServiceService } from 'src/app/service/user-service.service';

@Component({
  selector: 'app-boat-details',
  templateUrl: './boat-details.component.html',
  styleUrls: ['./boat-details.component.css']
})
export class BoatDetailsComponent implements OnInit {

  title: string;
  boat: Boat;
  boatPromotionUserRequest: BoatPromotionUserRequest = new BoatPromotionUserRequest;
  promotionUser: Boat = new Boat;
  email: any;
  role: any;
  isUser: boolean = false;
  promotionBoatUser: PromotionBoatUser = new PromotionBoatUser;

  constructor(private boatService: BoatServiceService, 
    private route: ActivatedRoute,
    private userService: UserServiceService) { }

  ngOnInit(): void {
    this.title = 'Boat details';

    this.role = localStorage.getItem('ROLES');
    if (this.role === 'ROLE_USER') {
      this.isUser = true;
    }

    this.boatService.getOne(this.route.snapshot.paramMap.get('id')).subscribe(data => {
      console.log(data);
      this.boat = data;
    });
  }

  subscribeToPromotions(boatId) {
    this.userService.subscribeToBoatPromotions(boatId).subscribe(data => {
      if(data===null){
        alert('Already subscribed!');
      }
      });
    }
}
