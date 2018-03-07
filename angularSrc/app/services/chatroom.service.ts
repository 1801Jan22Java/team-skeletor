import { Injectable } from '@angular/core';
import { Chatroom } from '../models/chatroom';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

@Injectable()
export class ChatroomService {

    currentChatroom;

    //I MAY NOT NEED THIS SERVICE AT ALL
    //
    // getChatroomTopics(): Observable<Chatroom[]>{
    //     return of(CHATROOMS);
    // }
  constructor() { }

}
