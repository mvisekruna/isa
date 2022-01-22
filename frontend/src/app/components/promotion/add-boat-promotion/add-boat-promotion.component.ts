import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AddPromotionRequest } from 'src/app/model/add-promotion-request';
import { PromotionBoatServiceService } from 'src/app/service/promotion-boat-service.service';
@Component({
  selector: 'app-add-boat-promotion',
  templateUrl: './add-boat-promotion.component.html',
  styleUrls: ['./add-boat-promotion.component.css']
})
export class AddBoatPromotionComponent implements OnInit {

  boatPromotionRequest: AddPromotionRequest;
  filterDateFrom: Date;
  filterDateTo: Date;

  constructor(private promotionBoatService: PromotionBoatServiceService, 
    private router: Router) { }

  ngOnInit(): void {
    this.boatPromotionRequest = new AddPromotionRequest;
    this.filterDateFrom = new Date();
    this.filterDateTo = new Date();
  }

  onSubmit(){
    console.log(this.boatPromotionRequest)
    this.filterDateFrom = new Date((<HTMLInputElement>document.getElementById("startPromo")).value);
    this.boatPromotionRequest.startPromo = this.filterDateFrom.toISOString().slice(0, 16).replace('T', ' ')+":00";
    this.filterDateTo = new Date((<HTMLInputElement>document.getElementById("endPromo")).value);
    this.boatPromotionRequest.endPromo = this.filterDateTo.toISOString().slice(0, 16).replace('T', ' ')+":00";
   
    this.promotionBoatService.addPromotionToBoat(this.boatPromotionRequest).subscribe( res => {
      console.log(res);
      if(res===null){
        alert('You cant add promotion to this boat!');
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
