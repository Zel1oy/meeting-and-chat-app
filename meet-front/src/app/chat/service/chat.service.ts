import { Injectable } from '@angular/core';
import {Chat} from "../model/chat";
import {Stomp} from "@stomp/stompjs";
import SockJS from "sockjs-client";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {CreateChatRequest} from "../model/create-chat-request";
import {Message} from "../model/message";

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  socket?: WebSocket;
  baseUrl: string = 'http://localhost:8080/api';

  constructor(private httpClient: HttpClient) { }

  loadChat(chatId: number): Observable<any> {
    console.log('Loading chat:', chatId);
    return this.httpClient.get<any>(this.baseUrl + '/chats/' + chatId + '/getMessages');
  }

  addChat(chat: CreateChatRequest): Observable<Chat> {
    console.log('Adding chat:', chat);
    return this.httpClient.post<any>(this.baseUrl + '/chats/create', chat);
  }

  getChatList(): Observable<Chat[]> {
    let token = sessionStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    return this.httpClient.get<any>(this.baseUrl + '/chats', { headers });
  }
}
