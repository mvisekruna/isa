import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AddPromotionRequest } from 'src/app/model/add-promotion-request';
import { AdventureServiceService } from 'src/app/service/adventure-service.service';
import { PromotionServiceService } from 'src/app/service/promotion-service.service';

@Component({
  selector: 'app-add-promotion',
  templateUrl: './add-promotion.component.html',
  styleUrls: ['./add-promotion.component.css']
})
export class AddPromotionComponent implements OnInit {

  promotionRequest: AddPromotionRequest;
  filterDateFrom: Date;
  filterDateTo: Date;

  constructor(private promotionService: PromotionServiceService, private router: Router) { }

  ngOnInit(): void {
    this.promotionRequest = new AddPromotionRequest;
    this.filterDateFrom = new Date();
    this.filterDateTo = new Date();
  }

  onSubmit(){
    console.log(this.promotionRequest)
    this.filterDateFrom = new Date((<HTMLInputElement>document.getElementById("startPromo")).value);
    this.promotionRequest.startPromo = this.filterDateFrom.toISOString().slice(0, 16).replace('T', ' ')+":00";
    this.filterDateTo = new Date((<HTMLInputElement>document.getElementById("endPromo")).value);
    this.promotionRequest.endPromo = this.filterDateTo.toISOString().slice(0, 16).replace('T', ' ')+":00";
   
    this.promotionService.addPromotionToAdventure(this.promotionRequest).subscribe( res => {
      console.log(res);
      if(res===null){
        alert('You cant add promotion to this adventure!');
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
