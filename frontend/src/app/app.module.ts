import { BrowserModule } from '@angular/platform-browser';
import { Injectable, NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { HttpClientModule, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { AuthServiceService } from './service/auth-service.service';
import { ApiServiceService } from './service/api-service.service';
import { UserServiceService } from './service/user-service.service';
import { CommonModule } from '@angular/common';
import { HomePageComponent } from './components/home-page/home-page.component';
import { MyProfileComponent } from './components/my-profile/my-profile.component';
import { JwtHelperService, JWT_OPTIONS } from '@auth0/angular-jwt';
import { SearchPipe } from './search.pipe';
import { DataTablesModule } from 'angular-datatables';
import { RegisterNewUserComponent } from './components/register-new-user/register-new-user.component';
import { AgmCoreModule } from '@agm/core';
import { TutorListComponent } from './components/tutor-list/tutor-list.component';
import { VacationHomeListComponent } from './components/vacation-home/vacation-home-list/vacation-home-list.component';
import { BoatListComponent } from './components/boat/boat-list/boat-list.component';
import { AdventureListComponent } from './components/adventure/adventure-list/adventure-list.component';
import { HomePageUserComponent } from './components/home-page/home-page-user/home-page-user.component';
import { HistoryComponent } from './components/history/history.component';
import { AdventureHistoryComponent } from './components/adventure/adventure-history/adventure-history.component';
import { HomePageAdminComponent } from './components/home-page/home-page-admin/home-page-admin.component';
import { PromotionListComponent } from './components/promotion/promotion-list/promotion-list.component';
import { AdventureDetailsComponent } from './components/adventure/adventure-details/adventure-details.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { BoatHistoryComponent } from './components/boat/boat-history/boat-history.component';
import { VacationHomeDetailsComponent } from './components/vacation-home/vacation-home-details/vacation-home-details.component';
import { VacationHomeHistoryComponent } from './components/vacation-home/vacation-home-history/vacation-home-history.component';
import { AddPromotionComponent } from './components/promotion/add-promotion/add-promotion.component';
import { BoatDetailsComponent } from './components/boat/boat-details/boat-details.component';
import { AddAdminComponent } from './components/add-admin/add-admin.component';
import { DeleteAccountRequestsComponent } from './components/delete-account-requests/delete-account-requests.component';
import { HomePageTutorComponent } from './components/home-page/home-page-tutor/home-page-tutor.component';
import { RegistrationForOthersFormComponent } from './components/registration-for-others-form/registration-for-others-form.component';
import { ChosenPromotionsComponent } from './components/promotion/chosen-promotions/chosen-promotions.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    RegisterFormComponent,
    HomePageComponent,
    MyProfileComponent,
    SearchPipe,
    RegisterNewUserComponent,
    ChangePasswordComponent,
    TutorListComponent,
    VacationHomeListComponent,
    BoatListComponent,
    AdventureListComponent,
    HomePageUserComponent,
    HistoryComponent,
    AddPromotionComponent,
    BoatHistoryComponent,
    AdventureHistoryComponent,
    VacationHomeHistoryComponent,
    HomePageAdminComponent,
    PromotionListComponent,
    BoatDetailsComponent,
    AdventureDetailsComponent,
    VacationHomeDetailsComponent,
    AddAdminComponent,
    DeleteAccountRequestsComponent,
    HomePageTutorComponent,
    RegistrationForOthersFormComponent,
    ChosenPromotionsComponent
   
  ],
  imports: [
    DataTablesModule,
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDMB95HndoUh1HWZJ0v2MFyWWQ-eaRWVGw'
    })
  ],
  providers: [
    AuthServiceService,
    UserServiceService,
    JwtHelperService,
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    ApiServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
@Injectable(
)
export class XhrInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const xhr = req.clone({
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
    });
    return next.handle(xhr);
  }
}