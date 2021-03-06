import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { DeleteAccountResponse } from 'src/app/model/delete-account-response';
import { DeleteAccounService } from 'src/app/service/delete-account.service.service';

@Component({
  selector: 'app-delete-account-requests',
  templateUrl: './delete-account-requests.component.html',
  styleUrls: ['./delete-account-requests.component.css']
})
export class DeleteAccountRequestsComponent implements OnInit {

  accountsForDelete: DeleteAccountResponse[];
  title: string;
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject<any>();
  terms: any;
  declineReason: string;
  confirmReason: string;
  reason: string = '';

  constructor(private deleteAcconutService: DeleteAccounService) { }

  ngOnInit(): void {
    
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      lengthMenu: [5, 10, 25],
      processing: true
    };
    this.title = 'Delete account requests';
    this.loadAll();

  }

  loadAll() {
    this.deleteAcconutService.loadAll().subscribe(data => {
      console.log(data);
      this.accountsForDelete = data;
     // this.dtTrigger.next();
    });
  }
  confirmRequest(id, reason) {
    console.log(reason);
    this.confirmReason = (<HTMLInputElement>document.getElementById("confirmReason")).value;
    this.deleteAcconutService.deleteApproved(id, reason).subscribe(data =>{
      console.log(data);
      this.loadAll();
    });
  }

  declineRequest(id, reason) {
    console.log(reason);
    this.declineReason = (<HTMLInputElement>document.getElementById("declineReason")).value;
    this.deleteAcconutService.deleteDenied(id, reason).subscribe(data =>{
      console.log(data);
      this.loadAll();
    });
  }



  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

}
