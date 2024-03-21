import {Component, OnInit} from '@angular/core';
import {Chat} from "./model/chat";
import {ChatService} from "./service/chat.service";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import { Stomp } from "@stomp/stompjs";
import SockJS from "sockjs-client";
import {CreateChatRequest} from "./model/create-chat-request";
import {CreateMessageRequest} from "./model/create-message-request";

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit {
  messageToSend: CreateMessageRequest = new CreateMessageRequest();
  chatList?: Chat[];
  selectedChat?: Chat;
  senderCheck!: string;

  addChatSelected: boolean = false;
  memberToAdd!: string;
  chatToAdd: CreateChatRequest = new CreateChatRequest();

  socket?: WebSocket;
  stompClient: any;
  subscribedChatIds: Set<number> = new Set<number>();
  baseUrl: string = 'http://localhost:8080/api';

  constructor(private chatService: ChatService) {
    if (sessionStorage.length === 0) {
      window.location.href = '/register';
    }
  }

  ngOnInit() {
    this.senderCheck = sessionStorage.getItem('email')!;
    this.chatService.getChatList().subscribe(
      response => {
        this.chatList = response;
      }, error => {
        console.error('Error fetching chat list:', error);
      }
    );
  }

  changeAddChatSelected() {
    this.chatService.getChatList().subscribe(
      response => {
        this.chatList = response;
      },
      error => {
        console.error('Error fetching chat list:', error);
      }
    );
    this.addChatSelected = !this.addChatSelected;
  }

  addMemberToChat(member: string) {
    console.log('Adding member:', member);
    console.log(this.chatToAdd.members)
    this.chatToAdd.members.push(member);
    console.log('Members:', this.chatToAdd.members);
  }

  addChat() {
    console.log('Adding chat:', this.chatToAdd);
    this.chatToAdd.members.push(sessionStorage.getItem('email')!);
    this.chatService.addChat(this.chatToAdd).subscribe(
      response => {
        this.chatToAdd.members = [];
      },
      error => {
        console.error('Error adding chat:', error);
      }
    );
  }

  sendMessage() {
    console.log('Sending message:', this.messageToSend);
    if (this.messageToSend.content !== '') {
      this.messageToSend.chat = this.selectedChat!.id;
      this.messageToSend.sender = sessionStorage.getItem('email')!;
      this.messageToSend.time = new Date();
      let messageJson = JSON.stringify(this.messageToSend);
      console.log('Sending message:', messageJson);
      this.stompClient!.send('/app/chat/' + this.selectedChat!.id + '/sendMessage', {}, messageJson);
    }
  }

  goToChat(chat: Chat) {
    this.selectedChat = chat;
    if (!this.subscribedChatIds.has(chat.id)) {
      this.connect(chat.id);
      this.subscribedChatIds.add(chat.id);
    }
    console.log('Selected chat:', this.selectedChat);
  }

  connect(chatId: number) {
    console.log("Initialize WebSocket Connection");
    this.socket = new SockJS(this.baseUrl + '/ws');
    this.stompClient = Stomp.over(this.socket);
    this.stompClient.connect({}, (frame: any) => {
      console.log("Connected: " + frame);
      this.stompClient.subscribe("/topic/chat/" + chatId, (message: any) => {
        console.log('Received message:', message);
        this.selectedChat!.messages.push(JSON.parse(message.body));
        console.log('Messages:', this.selectedChat!.messages);
      });
    })
  }
}
