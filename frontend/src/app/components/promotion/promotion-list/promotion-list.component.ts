import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { Promotion } from 'src/app/model/promotion';
import { PromotionServiceService } from 'src/app/service/promotion-service.service';

@Component({
  selector: 'app-promotion-list',
  templateUrl: './promotion-list.component.html',
  styleUrls: ['./promotion-list.component.css']
})
export class PromotionListComponent implements OnInit {

  dtOptions: DataTables.Settings = {};
  terms: any;
  promotions: Promotion[] = [];
  title: string;
  dtTrigger: Subject<any> = new Subject<any>();
  isUser: boolean = false;
  role: any;

  constructor(private promotionService: PromotionServiceService) {
    this.title = 'Promotions list'
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
    }
    this.promotionService.loadAllAdventurePromotions().subscribe(data => {
      console.log(data);
      this.promotions = data;
      this.dtTrigger.next();
    });
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

  subscribeFun(id:any): void {
    console.log("usao");
    this.promotionService.subscribeToPromotions(id).subscribe(data=> {
      console.log(data);
    });
    
  }

}
