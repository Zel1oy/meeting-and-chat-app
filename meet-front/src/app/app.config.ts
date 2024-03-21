import { ApplicationConfig } from '@angular/core';
import {provideRouter, Routes} from '@angular/router';
import { provideHttpClient} from "@angular/common/http";
import {ChatComponent} from "./chat/chat.component";
import {RegisterComponent} from "./user/register/register.component";
import {LoginComponent} from "./user/login/login.component";
import {LobbyComponent} from "./meeting/lobby/lobby.component";
import {CreateRoomComponent} from "./meeting/create-room/create-room.component";
import {RoomTemplateComponent} from "./meeting/room-template/room-template.component";

const ROUTES: Routes = [
  { path: 'chats', component: ChatComponent },
  { path: 'meetings', component: LobbyComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: '', component: LobbyComponent },
  { path: 'home', component: LobbyComponent },
  { path: 'meetings/create', component: CreateRoomComponent},
  { path: 'meeting/:id', component: RoomTemplateComponent }
];

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(ROUTES),
    provideHttpClient()
  ]
};
