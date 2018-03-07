import { Component, OnInit } from '@angular/core';
import { HttpService } from '../../services/http.service';
import { User } from '../../models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

    loggedUser: User;

  constructor(public _httpService: HttpService, public router: Router) {}

  ngOnInit() {
      this.loggedUser = JSON.parse(localStorage.getItem("currentUser"));
  }

}
