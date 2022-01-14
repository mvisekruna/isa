import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Adventure } from 'src/app/model/adventure';
import { AdventureServiceService } from 'src/app/service/adventure-service.service';

@Component({
  selector: 'app-adventure-details',
  templateUrl: './adventure-details.component.html',
  styleUrls: ['./adventure-details.component.css']
})
export class AdventureDetailsComponent implements OnInit {

  title: string;
  adventure: Adventure;

  constructor(private adventureService: AdventureServiceService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.title = 'Adventure details';
    this.adventureService.getOne(this.route.snapshot.paramMap.get('id')).subscribe(data => {
      console.log(data);
      this.adventure = data;
    });
  }

}
