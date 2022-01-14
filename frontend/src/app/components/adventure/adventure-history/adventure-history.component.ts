import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { AdventureReservationHistoryResponse } from 'src/app/model/adventure-reservation-history-response';
import { CancelReservationRequest } from 'src/app/model/cancel-reservation-request';
import { UserEmailRequest } from 'src/app/model/user-email-request';
import { ReservationServiceService } from 'src/app/service/reservation-service.service';

@Component({
  selector: 'app-adventure-history',
  templateUrl: './adventure-history.component.html',
  styleUrls: ['./adventure-history.component.css']
})
export class AdventureHistoryComponent implements OnInit {
  dtOptions: DataTables.Settings = {};
  terms: any;
  adventureReservations: AdventureReservationHistoryResponse[] = []; 
  title: string;
  dtTrigger: Subject<any> = new Subject<any>();
  email: any;
  userEmailRequest: UserEmailRequest;

  constructor(private reservationService : ReservationServiceService) {
    this.title = "Adventure reservation history";
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
      this.adventureReservations = data.adventureReservations;     
      this.dtTrigger.next();
    });
  }
  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

  cancelAdventureReservation(id: any) {
    var cancelReservationRequest: CancelReservationRequest = new CancelReservationRequest();
    cancelReservationRequest.email = this.email;
    cancelReservationRequest.id = id;

    this.reservationService.cancelAdventureReservation(cancelReservationRequest).subscribe(data => {
      this.adventureReservations = data.adventureReservations;
    });

  }
}
