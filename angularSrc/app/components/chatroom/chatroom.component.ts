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
        newMessage.user = JSON.parse(this.currentUser);
        newMessage.room = this.currentChatroom;
        newMessage.imageURL = 1;
        newMessage.date = null;
        this._httpService.createMessage(newMessage).subscribe(results => {
            this.router.navigate(['chatroom/' + this.chatroomId]);
        });
    }

    reportPost(messageId) {
        this.report.message = new Message()
        this.report.message.id = messageId;
        this.report.user = new User();
        this.report.user.id = this.currentUser.id;
        this.report.id = 0;
        this._httpService.reportMessage(this.report).subscribe(results => {
            this.router.navigate(['chatroom/' + this.chatroomId]);
        });
    }

    deletePost(messageId){
        this._httpService.deleteMessage(messageId).subscribe();
    }

    onClick(giphyImg: NgForm){
        this.gifService.getAGif(giphyImg.value.giphyBody).subscribe( results => {
            this.gifImage = results;
            let giphyMessage = new Message();
            giphyMessage.id = 0;
            giphyMessage.message = this.gifImage.data.image_url;
            giphyMessage.user = this.currentUser;
            giphyMessage.room = this.currentChatroom;
            giphyMessage.imageURL = 1;
            giphyMessage.date = null;
            this._httpService.createMessage(giphyMessage).subscribe(results => {
                this.router.navigate(['chatroom/' + this.chatroomId]);
            });
        });
    }

  constructor(private chatroomService: ChatroomService, private route: ActivatedRoute, private _httpService: HttpService, private messageService: MessageService, public router: Router, private gifService: GifService) {
      // Use +params to allow TypeScript convert Params type to number type
      this.route.params.subscribe( params => {
        this.chatroomId = +params['id'];
        this.currentChatroom = this.chatroomService.currentChatroom;
        this.messages = this.messageService.chatroomMessages;

      });

  }

  ngOnInit() {
      this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
      console.log("Current User from chatroom component " + this.currentUser);
  }
}
