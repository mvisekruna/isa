import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddAdminComponent } from './components/add-admin/add-admin.component';
import { AdventureDetailsComponent } from './components/adventure/adventure-details/adventure-details.component';
import { AdventureListComponent } from './components/adventure/adventure-list/adventure-list.component';
import { BoatDetailsComponent } from './components/boat/boat-details/boat-details.component';
import { BoatListComponent } from './components/boat/boat-list/boat-list.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { DeleteAccountRequestsComponent } from './components/delete-account-requests/delete-account-requests.component';
import { HistoryComponent } from './components/history/history.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { MyProfileComponent } from './components/my-profile/my-profile.component';
import { AddBoatPromotionComponent } from './components/promotion/add-boat-promotion/add-boat-promotion.component';
import { AddPromotionComponent } from './components/promotion/add-promotion/add-promotion.component';
import { AddVacationHomePromotionComponent } from './components/promotion/add-vacation-home-promotion/add-vacation-home-promotion.component';
import { ChosenPromotionsComponent } from './components/promotion/chosen-promotions/chosen-promotions.component';
import { PromotionListComponent } from './components/promotion/promotion-list/promotion-list.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { RegisterNewUserComponent } from './components/register-new-user/register-new-user.component';
import { RegistrationForOthersFormComponent } from './components/registration-for-others-form/registration-for-others-form.component';
import { TutorListComponent } from './components/tutor-list/tutor-list.component';
import { VacationHomeDetailsComponent } from './components/vacation-home/vacation-home-details/vacation-home-details.component';
import { VacationHomeListComponent } from './components/vacation-home/vacation-home-list/vacation-home-list.component';
import { GuardServiceService } from './service/guard-service.service';

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
    path: 'registrationforothers',
    component: RegistrationForOthersFormComponent
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
    component: VacationHomeListComponent,
    canActivate: [GuardServiceService],
    data: {
      expectedRole: 'ROLE_USER ROLE_VACATION_HOME_OWNER UNAUTHENTICATED'
    }
  },
  {
    path: 'boat-list',
    component: BoatListComponent,
    canActivate: [GuardServiceService],
    data: {
      expectedRole: 'ROLE_BOAT_OWNER ROLE_USER ROLE_TUTOR UNAUTHENTICATED'
    }
  },
  {
    path: 'adventure-list',
    component: AdventureListComponent,
    canActivate: [GuardServiceService],
    data: {
      expectedRole: 'ROLE_TUTOR ROLE_USER UNAUTHENTICATED'
    }
  },
  {
    path: 'add-promotion',
    component: AddPromotionComponent,
    canActivate: [GuardServiceService],
    data: {
      expectedRole: 'ROLE_TUTOR '
    } 
  },
  {
    path: 'add-boat-promotion',
    component: AddBoatPromotionComponent,
    canActivate: [GuardServiceService],
    data: {
      expectedRole: 'ROLE_BOAT_OWNER'
    } 
  },
  {
    path: 'add-vacation-home-promotion',
    component: AddVacationHomePromotionComponent,
    canActivate: [GuardServiceService],
    data: {
      expectedRole: 'ROLE_VACATION_HOME_OWNER'
    } 
  },
  {
    path: 'add-admin',
    component: AddAdminComponent,
    canActivate: [GuardServiceService],
    data: {
      expectedRole: 'ROLE_ADMIN'
    } 
  },
  {
    path: 'promotion-list',
    component: PromotionListComponent,
    canActivate: [GuardServiceService],
    data: {
      expectedRole: 'ROLE_USER ROLE_TUTOR ROLE_BOAT_OWNER ROLE_VACATION_HOME_OWNER'
    } 
  },
  {
    path: 'chosen-promotions-list',
    component: ChosenPromotionsComponent,
    canActivate: [GuardServiceService],
    data: {
      expectedRole: 'ROLE_USER'
    } 
  },
  {
    path: 'myprofile',
    component: MyProfileComponent,
    canActivate: [GuardServiceService],
    data: {
      expectedRole: 'ROLE_USER ROLE_TUTOR ROLE_VACATION_HOME_OWNER ROLE_BOAT_OWNER ROLE_ADMIN'
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
    path: 'delete-account-requests',
    component: DeleteAccountRequestsComponent,
    canActivate: [GuardServiceService],
    data: {
      expectedRole: 'ROLE_ADMIN'
    }
  },
  {
    path: 'boat/:id',
    component: BoatDetailsComponent,

  },
  {
    path: 'adventure/:id',
    component: AdventureDetailsComponent,

  },
  {
    path: 'vacationhome/:id',
    component: VacationHomeDetailsComponent,

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
