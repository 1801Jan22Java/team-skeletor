import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { AppRoutingModule } from './/app-routing.module';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LogoutComponent } from './components/logout/logout.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { ChatroomComponent } from './components/chatroom/chatroom.component';
import { ChatroomService } from './services/chatroom.service';
import { AllUsersComponent } from './components/all-users/all-users.component';
import { UsersService } from './services/users.service';
import { ReportsComponent } from './components/reports/reports.component';
import { ProfileComponent } from './components/profile/profile.component';
import { HttpService } from './services/http.service';
import { MessageService } from './services/message.service';
import { GifService } from './services/gif.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    LogoutComponent,
    NavbarComponent,
    HomepageComponent,
    ChatroomComponent,
    AllUsersComponent,
    ReportsComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    // Take out HTTP module if client works
    HttpModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [ChatroomService, UsersService, HttpService, MessageService, GifService],
  bootstrap: [AppComponent]
})
export class AppModule { }
