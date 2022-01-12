import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { MyProfileComponent } from './components/my-profile/my-profile.component';
import { GuardServiceService } from './service/guard-service.service';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { RegisterNewUserComponent } from './components/register-new-user/register-new-user.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { TutorListComponent } from './components/tutor-list/tutor-list.component';
import { VacationHomeListComponent } from './components/vacation-home-list/vacation-home-list.component';
import { AdventureListComponent } from './components/adventure-list/adventure-list.component';
import { HistoryComponent } from './components/history/history.component';
import { AddPromotionComponent } from './components/add-promotion/add-promotion.component';

const routes: Routes = [
  {
    path: 'loginform',
    component: LoginFormComponent
  },
  {
    path: 'changepassword',
    component: ChangePasswordComponent
  },
  {
    path: 'registerform',
    component: RegisterFormComponent
  },
  {
    path: 'homepage',
    component: HomePageComponent
  },
  {
    path: 'tutor-list',
    component: TutorListComponent
  },
  {
    path: 'vacation-home-list',
    component: VacationHomeListComponent
  },
  {
    path: 'adventure-list',
    component: AdventureListComponent
  },
  {
    path: 'add-promotion',
    component: AddPromotionComponent,
    canActivate: [GuardServiceService],
    data: {
      expectedRole: 'ROLE_ADMIN'
    } },

  {
    path: 'myprofile',
    component: MyProfileComponent,
    canActivate: [GuardServiceService],
    data: {
      expectedRole: 'ROLE_USER ROLE_TUTOR ROLE ROLE_VACATION_HOME_OWNER ROLE_BOAT_OWNER'
    }
  },
  {
    path: 'resrvationHistory',
    component: HistoryComponent,
    canActivate: [GuardServiceService],
    data: {
      expectedRole: 'ROLE_USER ROLE_TUTOR ROLE ROLE_VACATION_HOME_OWNER ROLE_BOAT_OWNER'
    }
  },
  {
    path: 'registernewuser',
    component: RegisterNewUserComponent,
    canActivate: [GuardServiceService],
    data: {
      expectedRole: 'ROLE_ADMIN'
    }
  },
  {
    path: '**',
    redirectTo: 'homepage',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
