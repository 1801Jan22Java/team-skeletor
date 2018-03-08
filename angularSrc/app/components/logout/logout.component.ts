import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UsersService } from '../../services/users.service';
import { HttpService } from '../../services/http.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(public router: Router, private usersService: UsersService, public _httpService: HttpService) { }

  //Automatically redirects to the login page after 3 seconds.
  ngOnInit() {
      this._httpService.logout().subscribe(results => {
          localStorage.clear();
          this._httpService.loggedInUser = null;
      });
      setTimeout((router: Router) => {
          this.router.navigate(['login']);
      }, 3000)
  }

}
