import { Component, OnInit } from '@angular/core';
import { HttpService } from '../../services/http.service';
import { Message } from "../../models/message";
import { Router } from '@angular/router';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {
    messageId;
    messages: Message[] = new Array<Message>();
    currentUser;

    getReports(){
        this._httpService.getMessageWithFiveReports().subscribe(results => {
            this.messageId = results;
            this.messages = [];
            for(let singleMsg in this.messageId){
                this._httpService.getMessageById(this.messageId[singleMsg]).subscribe(results => {
                    this.messages.push(results);
                })
            }
        });
    }

    deleteMessage(messageId){
        this._httpService.deleteReportByMessageId(messageId).subscribe(results => this.getReports());
    }

  constructor(public _httpService: HttpService, public router: Router) {
      if(!(localStorage.getItem("currentUser"))){
          this.router.navigateByUrl("/");
      } else {
          this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
          if(!this.currentUser.admin){
              this.router.navigateByUrl("/");
          }
      }
  }

  ngOnInit() {
      this.getReports();
      setInterval(() => {
          this.getReports();
      },30000);
  }

}
