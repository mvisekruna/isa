import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { CancelReservationRequest } from 'src/app/model/cancel-reservation-request';
import { UserEmailRequest } from 'src/app/model/user-email-request';
import { VacationHomeReservationHistoryResponse } from 'src/app/model/vacation-home-reservation-history-response';
import { ReservationServiceService } from 'src/app/service/reservation-service.service';

@Component({
  selector: 'app-vacation-home-history',
  templateUrl: './vacation-home-history.component.html',
  styleUrls: ['./vacation-home-history.component.css']
})
export class VacationHomeHistoryComponent implements OnInit {

  dtOptions: DataTables.Settings = {};
  terms: any;
  vacationHomeReservations: VacationHomeReservationHistoryResponse[] = []; 
  title: string;
  dtTrigger: Subject<any> = new Subject<any>();
  email: any;
  userEmailRequest: UserEmailRequest;

  constructor(private reservationService : ReservationServiceService ) {
    this.title = "Vacation home reservation history";
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
      this.vacationHomeReservations = data.vacationHomeReservations;
      this.dtTrigger.next();
    });
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

  cancelVacationHomeReservation(id: any) {
    var cancelReservationRequest: CancelReservationRequest = new CancelReservationRequest();
    cancelReservationRequest.email = this.email;
    cancelReservationRequest.id = id;

    this.reservationService.cancelVacationHomeReservation(cancelReservationRequest).subscribe(data => {
      this.vacationHomeReservations = data.vacationHomeReservations;
    });
  }
}
