import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { HttpService } from '../../services/http.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
    loggedUser;

  constructor(public _httpService: HttpService, public router: Router) {
  }

  ngOnInit() {
      this.loggedUser = this._httpService.loggedInUser;
  }

}
