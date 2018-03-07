import { User } from './user';
import { Chatroom } from './chatroom';

export class Message{
    id: number;
    user: User;
    room: Chatroom;
    message: string;
    date;
    imageURL: number;
}
