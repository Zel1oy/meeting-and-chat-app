import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {ChatComponent} from "./chat/chat.component";
import {RegisterComponent} from "./user/register/register.component";
import {LoginComponent} from "./user/login/login.component";
import {CreateRoomComponent} from "./meeting/create-room/create-room.component";
import {LobbyComponent} from "./meeting/lobby/lobby.component";
import {RoomTemplateComponent} from "./meeting/room-template/room-template.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ChatComponent, RegisterComponent, LoginComponent, CreateRoomComponent, LobbyComponent, RoomTemplateComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Meeting App';
}
