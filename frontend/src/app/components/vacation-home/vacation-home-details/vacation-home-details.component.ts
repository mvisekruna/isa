import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { VacationHome } from 'src/app/model/vacation-home';
import { VacationHomeServiceService } from 'src/app/service/vacation-home-service.service';

@Component({
  selector: 'app-vacation-home-details',
  templateUrl: './vacation-home-details.component.html',
  styleUrls: ['./vacation-home-details.component.css']
})
export class VacationHomeDetailsComponent implements OnInit {
  title: string;
  vacationHome: VacationHome;

  constructor(private vacationHomeService: VacationHomeServiceService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.title = 'Vacation home details';
    this.vacationHomeService.getOne(this.route.snapshot.paramMap.get('id')).subscribe(data => {
      console.log(data);
      this.vacationHome = data;
    });
  }

}
