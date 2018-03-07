import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import { MOCKUSERS } from '../users-mock-data';

@Injectable()
export class UsersService {

    loggedUser: User;

    getAllUsers(): Observable<User[]>{
        return of(MOCKUSERS);
    }

    getUserById(id): Observable<User>{
        return of(MOCKUSERS[id-1]);
    }

    logout():void{
        localStorage.clear();
    }

    psuedoLogin(id){
        localStorage.setItem("currentUser", JSON.stringify(MOCKUSERS[id-1]));
        this.loggedUser = JSON.parse(localStorage.getItem("currentUser"));
    }


  constructor() { }

}
