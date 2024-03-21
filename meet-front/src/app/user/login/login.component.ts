import { Component } from '@angular/core';
import {LoginRequest} from "../requests/login-request";
import {AuthService} from "../service/auth.service";
import {FormsModule} from "@angular/forms";
import {UserResponseDto} from "../../meeting/model/user-response-dto";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  userRequest: LoginRequest = new LoginRequest();
  myUser: UserResponseDto = new UserResponseDto();

  constructor(private authService: AuthService, private httpClient: HttpClient) { }

  async userLogin() {
    try {
      const response = await this.authService.loginUser(this.userRequest).toPromise();
      sessionStorage.setItem('token', response.token);

      await this.getAuthenticatedUser();

      sessionStorage.setItem('uid', this.myUser.id);
      sessionStorage.setItem('displayName', this.myUser.firstName + ' ' + this.myUser.lastName);
      sessionStorage.setItem('email', this.myUser.email);
    } catch (error) {
      console.log(error);
    }

    if (sessionStorage.getItem('token')) {
      window.location.href = '/meetings';
    }
  }

  private async getAuthenticatedUser() {
    let token = sessionStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    try {
      const response: any = await this.httpClient.get('http://localhost:8080/api/auth/me', { headers }).toPromise();
      this.myUser = response as UserResponseDto;
      console.log("getAuthenticatedUser:", this.myUser);
    } catch (error) {
      console.error('Error fetching user:', error);
    }
  }
}
