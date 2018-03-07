import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LogoutComponent } from './components/logout/logout.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { ChatroomComponent } from './components/chatroom/chatroom.component';
import { AllUsersComponent } from './components/all-users/all-users.component';
import { ReportsComponent } from './components/reports/reports.component';
import { ProfileComponent } from './components/profile/profile.component';

const routes: Routes = [
    { path: '', redirectTo:'/login', pathMatch:'full'},
    //{ path: 'home', component: HomepageComponent },
    { path: 'login', component: LoginComponent},
    { path: 'dashboard', component: DashboardComponent},
    { path: 'logout', component: LogoutComponent},
    { path: 'chatroom/:id', component: ChatroomComponent },
    { path: 'allUsers', component: AllUsersComponent},
    { path: 'reports', component: ReportsComponent},
    { path: 'profile', component: ProfileComponent},
]
@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
