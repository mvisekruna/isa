import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { Boat } from 'src/app/model/boat';
import { BoatServiceService } from 'src/app/service/boat-service.service';

@Component({
  selector: 'app-boat-list',
  templateUrl: './boat-list.component.html',
  styleUrls: ['./boat-list.component.css']
})
export class BoatListComponent implements OnInit {

  dtOptions: DataTables.Settings = {};
  terms: any;
  boats: Boat[] = [];
  title: string;
  dtTrigger: Subject<any> = new Subject<any>();

  constructor(private boatService: BoatServiceService, private router: Router) {
    this.title = 'Boat list';
  }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      lengthMenu: [5, 10, 25],
      processing: true
    };
    this.boatService.loadAll().subscribe(data => {
      console.log(data);
      this.boats = data;
      this.dtTrigger.next();
    });
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

  details(id) {
    this.router.navigate(['/boat',id]);  
  }
}
