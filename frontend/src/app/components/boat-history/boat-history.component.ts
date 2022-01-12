import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { BoatReservationHistoryResponse } from 'src/app/model/boat-reservation-history-response';
import { UserEmailRequest } from 'src/app/model/user-email-request';
import { ReservationServiceService } from 'src/app/service/reservation-service.service';

@Component({
  selector: 'app-boat-history',
  templateUrl: './boat-history.component.html',
  styleUrls: ['./boat-history.component.css']
})
export class BoatHistoryComponent implements OnInit {
  dtOptions: DataTables.Settings = {};
  terms: any;
  boatReservations: BoatReservationHistoryResponse[] = []; 
  title: string;
  dtTrigger: Subject<any> = new Subject<any>();
  email: any;
  userEmailRequest: UserEmailRequest;

  constructor(private reservationService : ReservationServiceService) {
    this.title = "Boat reservation history";
    this.email = localStorage.getItem('USERNAME');
    this.userEmailRequest = new UserEmailRequest();
    this.userEmailRequest.email = this.email;
   }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      lengthMenu : [5, 10, 25],
      processing: true
    };

    this.reservationService.getReservationHistory(this.userEmailRequest).subscribe(data => {
      this.boatReservations = data.boatReservations;     
      this.dtTrigger.next();
    });
  }
  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }
}
