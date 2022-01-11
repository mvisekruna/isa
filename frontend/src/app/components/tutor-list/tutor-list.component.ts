import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { UserServiceService } from 'src/app/service/user-service.service';

@Component({
  selector: 'app-tutor-list',
  templateUrl: './tutor-list.component.html',
  styleUrls: ['./tutor-list.component.css']
})
export class TutorListComponent implements OnInit {

  tutors: User[];
  title: string;

  constructor(private userService: UserServiceService) {
    this.title='Tutors list';
   }

  ngOnInit(): void {
    this.userService.loadAllTutors().subscribe(data => {
      this.tutors = data;
    })
  }

}
