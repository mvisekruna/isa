import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AddPromotionRequest } from 'src/app/model/add-promotion-request';
import { PromotionVacationHomeServiceService } from 'src/app/service/promotion-vacation-home-service.service';

@Component({
  selector: 'app-add-vacation-home-promotion',
  templateUrl: './add-vacation-home-promotion.component.html',
  styleUrls: ['./add-vacation-home-promotion.component.css']
})
export class AddVacationHomePromotionComponent implements OnInit {

  promotionRequest: AddPromotionRequest;
  filterDateFrom: Date;
  filterDateTo: Date;

  constructor(private promotionService: PromotionVacationHomeServiceService,
    private router: Router) { }

  ngOnInit(): void {
    this.promotionRequest = new AddPromotionRequest;
    this.filterDateFrom = new Date();
    this.filterDateTo = new Date();
  }

  onSubmit() {
    console.log(this.promotionRequest)
    this.filterDateFrom = new Date((<HTMLInputElement>document.getElementById("startPromo")).value);
    this.promotionRequest.startPromo = this.filterDateFrom.toISOString().slice(0, 16).replace('T', ' ')+":00";
    this.filterDateTo = new Date((<HTMLInputElement>document.getElementById("endPromo")).value);
    this.promotionRequest.endPromo = this.filterDateTo.toISOString().slice(0, 16).replace('T', ' ')+":00";
   
    this.promotionService.addPromotionToVacationHome(this.promotionRequest).subscribe( res => {
      console.log(res);
      if(res===null){
        alert('You cant add promotion to this vacation home!');
      }
      this.router.navigate(["/homepage"]);
    });  
  }

  get startPromo():string
  {
    //Strip the timezone letter 'Z' from the string;
    
    return this.filterDateFrom.toISOString().slice(0, 16).replace('T', ' ')+":00";  
  }
  set startPromo(value:string)
  {
    this.filterDateFrom = new Date(value);
  }
  get endPromo():string
  {
    //Strip the timezone letter 'Z' from the string;
    
    return this.filterDateTo.toISOString().slice(0, 16).replace('T', ' ')+":00";  
  }
  set endPromo(value:string)
  {
    this.filterDateTo = new Date(value);
  }

}
