import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { VacationHome } from 'src/app/model/vacation-home';
import { VacationHomeServiceService } from 'src/app/service/vacation-home-service.service';

@Component({
  selector: 'app-vacation-home-list',
  templateUrl: './vacation-home-list.component.html',
  styleUrls: ['./vacation-home-list.component.css']
})
export class VacationHomeListComponent implements OnInit {

  dtOptions: DataTables.Settings = {};
  terms: any;
  vacationHomes : VacationHome[] = [];
  title: string;
  dtTrigger: Subject<any> = new Subject<any>();

  constructor(private vacationHomeService: VacationHomeServiceService, private router: Router) {
    this.title = 'Vacation home list'
   }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      lengthMenu : [5, 10, 25],
      processing: true
    };
    this.vacationHomeService.loadAll().subscribe(data => {
      console.log(data);
      this.vacationHomes = data;
      this.dtTrigger.next();
    });
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

  details(id) {
    this.router.navigate(['/vacationhome',id]);  
  }
}
