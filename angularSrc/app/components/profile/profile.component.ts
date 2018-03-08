import { Component, OnInit } from '@angular/core';
import { HttpService } from '../../services/http.service';
import { User } from '../../models/user';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

    loggedUser: User;
    errorMessage: string;

  constructor(public _httpService: HttpService, public router: Router) {}

  checkLoggedIn(){
      if(localStorage.length != 1){
          this.router.navigate(['']);
      } else {
          this.loggedUser = JSON.parse(localStorage.getItem("currentUser"));
      }
  }

  onSubmit(updateUser: NgForm){
      if(updateUser.value.password){
          if(updateUser.value.password == updateUser.value.confirmPassword){
              this.loggedUser.password =  updateUser.value.password;
          } else {
              updateUser.reset();
              this.errorMessage = "Passwords do not match"
              return;
          }
      }
      if(updateUser.value.email){
          this.loggedUser.emailAddress = updateUser.value.email;
      }
      console.log(this.loggedUser);
      this._httpService.updateUser(this.loggedUser).subscribe(results => {
          localStorage.clear();
          localStorage.setItem("currentUser", JSON.stringify(this.loggedUser));
          updateUser.reset();
          this.errorMessage = "";
          this.checkLoggedIn()
      });
  }

  ngOnInit() {
      this.checkLoggedIn();
  }

}
