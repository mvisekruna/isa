import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { Adventure } from 'src/app/model/adventure';
import { AdventureServiceService } from 'src/app/service/adventure-service.service';

@Component({
  selector: 'app-adventure-list',
  templateUrl: './adventure-list.component.html',
  styleUrls: ['./adventure-list.component.css']
})
export class AdventureListComponent implements OnInit {

  dtOptions: DataTables.Settings = {};
  terms: any;
  adventures: Adventure[] = [];
  title: string;
  dtTrigger: Subject<any> = new Subject<any>();

  constructor(private adventureService: AdventureServiceService, private router: Router) {
    this.title = 'Adventure list';  
  }

  ngOnInit(): void {
      this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      lengthMenu : [5, 10, 25],
      processing: true
    };
    this.adventureService.loadAll().subscribe(data => {
      console.log(data);
      this.adventures = data;
      this.dtTrigger.next();
    });
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

  details(id) {
    this.router.navigate(['/adventure',id]);  
  }
}
