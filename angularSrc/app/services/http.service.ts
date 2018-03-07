import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';
import { Chatroom } from '../models/chatroom';
import { Message } from "../models/message";
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

const httpOptions = {
headers: new HttpHeaders({
        'Content-Type':  'application/json',
    })
};


@Injectable()
export class HttpService {

  constructor(private http: HttpClient, public router: Router, private _http: Http) { }

  loggedInUser: User;

  //NEED TO CHANGE URL TO PROJECT URL
  url: string = "http://localhost:8084/SkeletorSlums/";

  //DONE
  getAllUsers(){
      return this.http.get(this.url + '/user/all');
  }

  //DONE
  getUserById(id): Observable<User>{
      return this.http.get<User>(this.url + '/user/id/' + id);
  }

  //DONE
  processLogin(username, password): Observable<User>{
      return this.http.post<User>(this.url + 'login/login', {username: username, password: password}, httpOptions);
  }

  //DONE
  processRegister(username, email, password){
      console.log("Received info from the HttpService, username: " + username + ", email: " + email + ", password: " + password);
      return this.http.post(this.url + "user/addUser", {"id": 0, "username": username, "password": password, "emailAddress": email, "imageId":1, "admin":false, "active":true, "profileImageURL":1 }, httpOptions);
  }

  // Chatroom Requests

  // DONE
  addChatroom(chatroomTitle){
      return this.http.post(this.url + "chatroom/addChatroom", {name: chatroomTitle}, httpOptions);
  }


  //DONE
  deleteChatroom(id){
      return this.http.delete(this.url + "chatroom/delete/" + id);
  }

  //DONE
  getChatroomTopics(){
      return this.http.get(this.url + "chatroom/all");
  }

  //DONE
  getChatroomById(id): Observable<Chatroom>{
      return this.http.get<Chatroom>(this.url + "chatroom/" + id);
  }

  // Various Message Requests
  //done
  getChatroomMessages(id): Observable<Message[]>{
      return this.http.get<Message[]>(this.url + 'message/chatroom/' + id);
  }


  //DONE, but doesn't work if there are reports.
  deleteMessage(id){
      return this.http.delete(this.url + 'message/delete/' + id);
  }


  // DONE
  createMessage(message){
    console.log(message);
      return this.http.post(this.url + 'message/addMessage', message, httpOptions);
  }

  //DONE
  getMessageById(id): Observable<Message>{
      return this.http.get<Message>(this.url + 'message/' + id);
  }

  //DONE
  reportMessage(report){
      return this.http.post(this.url + 'report/addReport', report, httpOptions);
  }

  getMessageWithFiveReports(){
      return this.http.get(this.url + 'report/fiveReports');
  }

  //Done? Still tries to parse the returned object
  logout(){
      return this.http.get(this.url + 'login/logout');
  }

}
