import { Component, OnInit } from '@angular/core';
import { HttpService } from '../../services/http.service';
import { Message } from "../../models/message";

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {
    messageId;
    messages: Message[] = new Array<Message>();

  constructor(public _httpService: HttpService) {
      this._httpService.getMessageWithFiveReports().subscribe(results => {
          this.messageId = results;
          console.log(this.messageId);
          this._httpService.getMessageById(this.messageId).subscribe(results => {
              this.messages.push(results);
          })
      });
  }

  ngOnInit() {
  }

}
