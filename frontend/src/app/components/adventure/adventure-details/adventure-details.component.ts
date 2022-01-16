import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Adventure } from 'src/app/model/adventure';
import { AdventurePromotionUserRequest } from 'src/app/model/adventure-promotion-user-request';
import { PromotionAdventureUser } from 'src/app/model/promotion-adventure-user';
import { User } from 'src/app/model/user';
import { AdventureServiceService } from 'src/app/service/adventure-service.service';
import { PromotionServiceService } from 'src/app/service/promotion-service.service';
import { UserServiceService } from 'src/app/service/user-service.service';

@Component({
  selector: 'app-adventure-details',
  templateUrl: './adventure-details.component.html',
  styleUrls: ['./adventure-details.component.css']
})
export class AdventureDetailsComponent implements OnInit {

  title: string;
  adventure: Adventure;
  adventurePromotionUserRequest: AdventurePromotionUserRequest = new AdventurePromotionUserRequest;
  promotionUser: User = new User;
  email: any;
  role: any;
  isUser: boolean = false;
  promotionAdventureUser: PromotionAdventureUser = new PromotionAdventureUser;


  constructor(private adventureService: AdventureServiceService, private route: ActivatedRoute,
    private promotionService: PromotionServiceService, private userService: UserServiceService) { }

  ngOnInit(): void {
    this.title = 'Adventure details';

    this.role = localStorage.getItem('ROLES');
    if (this.role === 'ROLE_USER') {
      this.isUser = true;
    }

    this.adventureService.getOne(this.route.snapshot.paramMap.get('id')).subscribe(data => {
      console.log(data);
      this.adventure = data;
    });
  }

  subscribeToPromotions(): void {

    this.adventurePromotionUserRequest.adventureId = this.adventure.id;

    this.email = localStorage.getItem('USERNAME');
    this.userService.findByEmail(this.email).subscribe(data => {
      this.promotionUser = data;
    });
    this.adventurePromotionUserRequest.promotionUserId = this.promotionUser.id;

    this.promotionService.subscribeToPromotions(this.adventurePromotionUserRequest).subscribe(data => {
      if(data===null){
        alert('Alerady subscribed!')
      }
    });

    
  }
}
