import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { Promotion } from 'src/app/model/promotion';
import { PromotionBoatServiceService } from 'src/app/service/promotion-boat-service.service';
import { PromotionServiceService } from 'src/app/service/promotion-service.service';

@Component({
  selector: 'app-chosen-promotions',
  templateUrl: './chosen-promotions.component.html',
  styleUrls: ['./chosen-promotions.component.css']
})
export class ChosenPromotionsComponent implements OnInit {

  dtOptions: DataTables.Settings = {};
  terms: any;
  role: any;
  adventurePromotions: Promotion[] = [];
  boatPromotions: Promotion[] = [];
  vacationHomePromotions: Promotion[] = [];

  title1: string;
  title2: string;
  title3: string;

  dtTrigger1: Subject<any> = new Subject<any>();
  dtTrigger2: Subject<any> = new Subject<any>();
  dtTrigger3: Subject<any> = new Subject<any>();

  constructor(private promotionService: PromotionServiceService,
    private promotionBoatServiceService: PromotionBoatServiceService) {
    this.title1 = 'My chosen promotions';
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

    this.promotionService.findAllPromotionsIChose().subscribe(data => {
      this.adventurePromotions = data;
      console.log(this.adventurePromotions);
      this.dtTrigger1.next();
    });

    this.promotionBoatServiceService.findAllBoatPromotionsIChose().subscribe(data => {
      this.boatPromotions = data;
      this.dtTrigger2.next();
    });
  }

  cancelPromotion(id: any) {
    this.promotionService.cancelThePromotion(id).subscribe( data => {
      console.log(data);
    });
  }
  cancelBoatPromotion(id: any) {
    this.promotionBoatServiceService.cancelThePromotion(id).subscribe( data => {
      console.log(data);
    });
  }

}
