import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { Adventure } from 'src/app/model/adventure';
import { PromotionAdventureUser } from 'src/app/model/promotion-adventure-user';
import { AdventureServiceService } from 'src/app/service/adventure-service.service';
import { PromotionServiceService } from 'src/app/service/promotion-service.service';
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
  promotionAdventureUser: PromotionAdventureUser = new PromotionAdventureUser;
  title: string;
  dtTrigger1: Subject<any> = new Subject<any>();
  dtTrigger2: Subject<any> = new Subject<any>();
  role: any;
  isUser: boolean = false;

  constructor(private adventureService: AdventureServiceService, 
    private router: Router, 
    private promotionService: PromotionServiceService,
    private userService: UserServiceService) {
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
      this.userService.findMySubscribed().subscribe(data => {
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

  cancelSubscription(id) {
    this.userService.cancelMySubscription(id).subscribe(data => {
      console.log(data);
    });
    window.location.reload();

  }
}
