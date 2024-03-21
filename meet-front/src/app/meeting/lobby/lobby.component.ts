import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DatePipe, NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-lobby',
  standalone: true,
  imports: [
    NgForOf,
    DatePipe,
    NgIf
  ],
  templateUrl: './lobby.component.html',
  styleUrl: './lobby.component.css'
})
export class LobbyComponent {
  baseUrl = 'http://localhost:8080/api/meetings';
  meetingList: any;
  private datePipe: DatePipe = new DatePipe('en-US');

  constructor(private httpClient: HttpClient) {
    if (sessionStorage.length === 0) {
      window.location.href = '/register';
    }
    this.getAllMeetings()
  }

  getAllMeetings() {
    this.httpClient.get(this.baseUrl).subscribe(
      response => {
        this.meetingList = response;
      }, error => {
        console.log(error);
      }
    );
  }

  shouldDisplayMeeting(meetingEndTime: string): boolean {
    const currentTime = new Date();
    const currentTimeISO = <string>this.datePipe.transform(currentTime, 'yyyy-MM-ddTHH:mm:ss');
    return meetingEndTime < currentTimeISO;
  }

  joinRoom(meetingId: number) {
    window.location.href = '/meeting/' + meetingId;
  }
}
