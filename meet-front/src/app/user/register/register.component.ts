import { Component } from '@angular/core';
import {RegisterRequest} from "../requests/register-request";
import {AuthService} from "../service/auth.service";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  userRequest: RegisterRequest = new RegisterRequest();

  constructor(private authService: AuthService) {
    console.log('RegisterComponent created');
  }

  userRegister() {
    let userRegisterResponse;
    this.authService.registerUser(this.userRequest).subscribe(
      {
        next: response => {
          userRegisterResponse = response;
          if (userRegisterResponse != null) {
            window.location.href = '/login';
          }
        },
        error: error => console.log(error)
      }
    );
    if (userRegisterResponse != null) {
      window.location.href = '/login';
    }
  }
}
