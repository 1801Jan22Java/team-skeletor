import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpService } from '../../services/http.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    login: boolean = true;

    showRegister(): void{
        this.login = this.login ? false : true;
    }

    //After attempting to login, the admin boolean is stored into the localStorage so other components can access it.

    //Automatically redirects to dashboard after login
    onSubmit(login: NgForm){
        this._httpService.processLogin(login.value.username, login.value.password).subscribe(
            (results: any) => {
                localStorage.setItem("currentUser", JSON.stringify(results));
                this._httpService.loggedInUser = JSON.parse(localStorage.getItem("currentUser"));
                if(localStorage.length == 1){
                    this.router.navigate(["dashboard"]);
                }
            },
            error => console.log("Wrong Credentials")
        );

    }

    //Done
    onClick(register: NgForm){
        this._httpService.processRegister(register.value.username, register.value.email, register.value.password).subscribe( results => {
            this.router.navigate(["dashboard"]);
        });
    }

  constructor(private _httpService: HttpService, public router: Router) { }

  ngOnInit() {
  }

}
