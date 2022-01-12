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
import { ChangePasswordComponent } from './components/change-password/change-password.component';

import { AgmCoreModule } from '@agm/core';
import { TutorListComponent } from './components/tutor-list/tutor-list.component';
import { VacationHomeListComponent } from './components/vacation-home-list/vacation-home-list.component';
import { BoatListComponent } from './components/boat-list/boat-list.component';
import { AdventureListComponent } from './components/adventure-list/adventure-list.component';
import { HomePageUserComponent } from './components/home-page/home-page-user/home-page-user.component';
import { HistoryComponent } from './components/history/history.component';
import { AddPromotionComponent } from './components/add-promotion/add-promotion.component';
import { BoatHistoryComponent } from './components/boat-history/boat-history.component';
import { AdventureHistoryComponent } from './components/adventure-history/adventure-history.component';
import { VacationHomeHistoryComponent } from './components/vacation-home-history/vacation-home-history.component';


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
    VacationHomeHistoryComponent
   
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