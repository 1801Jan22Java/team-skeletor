import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ChatroomService } from '../../services/chatroom.service';
import { Chatroom } from '../../models/chatroom';
import { HttpService } from '../../services/http.service';
import { Report } from '../../models/report';
import { Message } from '../../models/message';
import { User } from '../../models/user';
import { MessageService } from '../../services/message.service';
import { GifService } from '../../services/gif.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-chatroom',
  templateUrl: './chatroom.component.html',
  styleUrls: ['./chatroom.component.css']
})
export class ChatroomComponent implements OnInit {

    chatroomId: number;
    messages;
    currentUser;
    report: Report = new Report();
    currentChatroom;
    gifImage;

    onSubmit(addMessage: NgForm){
        let newMessage = new Message();
        newMessage.id = 0;
        newMessage.message = addMessage.value.messageBody;
        newMessage.user = this.currentUser;
        newMessage.room = this.currentChatroom;
        newMessage.imageURL = 1;
        newMessage.date = null;
        this._httpService.createMessage(newMessage).subscribe(results => {
            addMessage.reset();
            this.grabMessages();
        });
    }

    reportPost(messageId) {
        this.report.message = new Message()
        this.report.message.id = messageId;
        this.report.user = new User();
        this.report.user.id = this.currentUser.id;
        this.report.id = 0;
        this._httpService.reportMessage(this.report).subscribe();
    }

    deletePost(messageId){
        this._httpService.deleteReportByMessageId(messageId).subscribe(results => {
            this._httpService.deleteMessage(messageId).subscribe(results => {
                this.grabMessages();
            });
        });
    }

    onClick(giphyImg: NgForm){
        this.gifService.getAGif(giphyImg.value.giphyBody).subscribe( results => {
            this.gifImage = results;
            let giphyMessage = new Message();
            giphyMessage.id = 0;
            giphyMessage.message = "";
            giphyMessage.user = this.currentUser;
            giphyMessage.room = this.currentChatroom;
            giphyMessage.imageURL = this.gifImage.data.image_url;
            giphyMessage.date = null;
            this._httpService.createMessage(giphyMessage).subscribe(results => {
                giphyImg.reset();
                this.grabMessages();
            });
        });
    }

    grabMessages(){
        this._httpService.getChatroomMessages(this.chatroomId).subscribe(results => this.messages = results);
    }

  constructor(private chatroomService: ChatroomService, private route: ActivatedRoute, private _httpService: HttpService, private messageService: MessageService, public router: Router, private gifService: GifService) {
      // Use +params to allow TypeScript convert Params type to number type
      this.route.params.subscribe( params => {
        this.chatroomId = +params['id'];
        this.currentChatroom = this.chatroomService.currentChatroom;
        this.messages = this.messageService.chatroomMessages;
    });
  }

  checkLoggedIn(){
      if(localStorage.length != 1){
          this.router.navigate(['']);
      } else {
          this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
      }
  }

  ngOnInit() {
      this.checkLoggedIn();
      setInterval(() => {
          this.grabMessages();
      },10000)

  }
}
