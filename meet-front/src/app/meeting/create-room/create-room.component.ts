import { Component } from '@angular/core';
import {FormsModule, NgForm} from "@angular/forms";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-create-room',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './create-room.component.html',
  styleUrl: './create-room.component.css'
})
export class CreateRoomComponent {
  private baseUrl = "http://localhost:8080/api/meetings";
  constructor(private httpClient: HttpClient) { }

  createMeeting(conferenceData: NgForm) {
    console.log(conferenceData.value);
    this.httpClient.post(this.baseUrl, conferenceData.value).subscribe(
      response => {
        console.log('Form data submitted successfully:', response);
        conferenceData.resetForm();
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
