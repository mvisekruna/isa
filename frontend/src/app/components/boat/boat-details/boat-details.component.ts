import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Boat } from 'src/app/model/boat';
import { BoatServiceService } from 'src/app/service/boat-service.service';

@Component({
  selector: 'app-boat-details',
  templateUrl: './boat-details.component.html',
  styleUrls: ['./boat-details.component.css']
})
export class BoatDetailsComponent implements OnInit {

  title: string;
  boat: Boat;

  constructor(private boatService: BoatServiceService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.title = 'Boat details';

    this.boatService.getOne(this.route.snapshot.paramMap.get('id')).subscribe(data => {
      console.log(data);
      this.boat = data;
    });
  }

}
