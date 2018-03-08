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
  url: string = "http://ec2-54-145-228-182.compute-1.amazonaws.com:8084/SkeletorSlums/";


  getAllUsers(){
      return this.http.get(this.url + '/user/all');
  }


  getUserById(id): Observable<User>{
      return this.http.get<User>(this.url + '/user/id/' + id);
  }

  updateUser(user){
      return this.http.put(this.url + "user/updateUser", user, httpOptions);
  }

  banUserById(id){
      return this.http.post(this.url + "user/banUser", id, httpOptions);
  }


  processLogin(username, password): Observable<User>{
      return this.http.post<User>(this.url + 'login/login', {username: username, password: password}, httpOptions);
  }


  processRegister(username, email, password){
      return this.http.post(this.url + "user/addUser", {"id": 0, "username": username, "password": password, "emailAddress": email, "imageId":1, "admin":false, "active":true, "profileImageURL":1 }, httpOptions);
  }

  // Chatroom Requests


  addChatroom(chatroomTitle){
      return this.http.post(this.url + "chatroom/addChatroom", {name: chatroomTitle}, httpOptions);
  }



  deleteChatroom(id){
      return this.http.delete(this.url + "chatroom/delete/" + id);
  }


  getChatroomTopics(){
      return this.http.get(this.url + "chatroom/all");
  }


  getChatroomById(id): Observable<Chatroom>{
      return this.http.get<Chatroom>(this.url + "chatroom/" + id);
  }

  deleteMessageByChatroomId(id) {
      return this.http.delete(this.url + "message/delete/chatroom/" + id);
  }

  // Various Message Requests

  getChatroomMessages(id): Observable<Message[]>{
      return this.http.get<Message[]>(this.url + 'message/chatroom/' + id);
  }



  deleteMessage(id){
      return this.http.delete(this.url + 'message/delete/' + id);
  }



  createMessage(message){
      return this.http.post(this.url + 'message/addMessage', message, httpOptions);
  }


  getMessageById(id): Observable<Message>{
      return this.http.get<Message>(this.url + 'message/' + id);
  }


  reportMessage(report){
      return this.http.post(this.url + 'report/addReport', report, httpOptions);
  }


  getMessageWithFiveReports(){
      return this.http.get(this.url + 'report/fiveReports');
  }

  deleteReportByMessageId(messageId){
      return this.http.delete(this.url + 'report/delete/message/' + messageId);
  }

  
  logout(){
      return this.http.get(this.url + 'login/logout');
  }



}
