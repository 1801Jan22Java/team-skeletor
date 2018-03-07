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

  loggedUser;
  chatrooms;
  chatroomMessages;
  create: boolean = false;
  chatroomTopic: string;
  newChatroom: Chatroom = new Chatroom();

  showAdd(){
    this.create = true;
  }


  deleteChatroom(id) {
      this._httpService.getChatroomMessages(id).subscribe( results => {
          this.chatroomMessages = results;
          for(let message in this.chatroomMessages){
              console.log(this.chatroomMessages[message]);
              this._httpService.deleteReportByMessageId(this.chatroomMessages[message].id).subscribe();
          }
          this._httpService.deleteMessageByChatroomId(id).subscribe( results => {
              this._httpService.deleteChatroom(id).subscribe( results => this.getChatroomTopics() );
          })
      })
  }

  getChatroomTopics(): void {
      this._httpService.getChatroomTopics().subscribe(chatrooms => {
          this.chatrooms = chatrooms;
      });
  }

  addChatroom(name){
      this._httpService.addChatroom(name).subscribe( results => this.getChatroomTopics() )
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

  checkLoggedIn(){
      if(localStorage.length != 1){
          this.router.navigate(['']);
      } else {
          this.loggedUser = JSON.parse(localStorage.getItem("currentUser"));
      }
  }


  ngOnInit() {
      this.getChatroomTopics();
      this.checkLoggedIn();

  }

}
