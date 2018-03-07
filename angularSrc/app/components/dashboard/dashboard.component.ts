import { Component, OnInit, Inject } from '@angular/core';
import { Chatroom } from '../../models/chatroom';
import { ChatroomService } from '../../services/chatroom.service';
import { Router } from '@angular/router';
import { User } from '../../models/user';
import { UsersService } from '../../services/users.service';
import { HttpService } from '../../services/http.service';
import { MessageService } from '../../services/message.service';

import{ NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {

  constructor(private chatroomService: ChatroomService, private usersService: UsersService, public router: Router, private _httpService: HttpService, private messageService: MessageService) {
  }

  //Hard coded our first user. Need to implement it as a service so it will be passed around to other components
  //It will contain the information of the logged in user

  loggedUser;
  chatrooms;
  chatroomTopics;
  // The integer is to test user mock data login
  id: number = 1;
  create: boolean = false;

  chatroomTopic: string;
  newChatroom: Chatroom = new Chatroom();

  showAdd(){
    this.create = true;
  }

  //NOT RELOADING/REDIRECTING TO NEW PAGE
  deleteChatroom(id) {
      this._httpService.deleteChatroom(id).subscribe( results => this.router.navigate(['dashboard']));
  }

  getChatroomTopics(): void {
      this._httpService.getChatroomTopics().subscribe(chatrooms => {
          this.chatrooms = chatrooms;
      });
  }

  addChatroom(name){
      this._httpService.addChatroom(name).subscribe( results => this.router.navigate(['dashboard']))
  }

  goToChatroom(id){
      this._httpService.getChatroomById(id).subscribe(chatroom => {
          this.chatroomService.currentChatroom = chatroom;
          this._httpService.getChatroomMessages(id).subscribe( message => {
              this.messageService.chatroomMessages = message;
              this.router.navigate(['chatroom/' + id]);
          })
      });
  }

  ngOnInit() {
      this.getChatroomTopics();
      this.loggedUser = JSON.parse(localStorage.getItem("currentUser"));

  }

}
