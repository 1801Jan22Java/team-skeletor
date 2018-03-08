import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UsersService } from '../../services/users.service';
import { User } from '../../models/user';
import { HttpService } from '../../services/http.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.css']
})
export class AllUsersComponent implements OnInit {

    allUsers;
    showUser: User;
    currentUser;
    errorMessage;

    getAllUsers(): void {
        this._httpService.getAllUsers().subscribe(returnedUsers => this.allUsers = returnedUsers);
    }

    getUserById(id): void{
        this._httpService.getUserById(id).subscribe(returnedUser => {
            this.showUser = returnedUser;
        });
    }

    banUser(id){
        this._httpService.banUserById(id).subscribe(results => {
            this.showUser = null;
            this.getAllUsers();
        });
    }

    onSubmit(updateUser: NgForm){
        if(updateUser.value.password && updateUser.value.email){
            if(updateUser.value.password == updateUser.value.confirmPassword){
                this.showUser.password =  updateUser.value.password;
            } else {
                updateUser.reset();
                this.errorMessage = "Passwords to not match";
                return;
            }
        }
        if(updateUser.value.email){
            this.showUser.emailAddress = updateUser.value.email;
        }
        this._httpService.updateUser(this.showUser).subscribe(results => {
            updateUser.reset();
            this.errorMessage = "";
            this.getAllUsers()
        });
    }


  constructor(public users: UsersService, public route: ActivatedRoute, private _httpService: HttpService, public router: Router) {
      if(!(localStorage.getItem("currentUser"))){
          this.router.navigateByUrl("/");
      } else {
          this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
          if(!this.currentUser.admin){
              this.router.navigateByUrl("/");
          }
      }
  }

  ngOnInit() {
      this.getAllUsers();
  }

}
