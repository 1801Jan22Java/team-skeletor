import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UsersService } from '../../services/users.service';
import { User } from '../../models/user';
import { HttpService } from '../../services/http.service';

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.css']
})
export class AllUsersComponent implements OnInit {

    allUsers;
    showUser: User;
    currentUser;

    getAllUsers(): void {
        this._httpService.getAllUsers().subscribe(returnedUsers => this.allUsers = returnedUsers);
    }

    getUserById(id): void{
        this._httpService.getUserById(id).subscribe(returnedUser => {
            this.showUser = returnedUser;
        });
    }

    banUser(id){
        console.log("Ban the user with id of " + id);
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
