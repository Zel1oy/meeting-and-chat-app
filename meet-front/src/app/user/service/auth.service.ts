import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginRequest} from "../requests/login-request";
import {RegisterRequest} from "../requests/register-request";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseLoginUrl = 'http://localhost:8080/api/auth';

  constructor(private httpClient: HttpClient) { }

  loginUser(user: LoginRequest): Observable<any> {
    return this.httpClient.post(this.baseLoginUrl + '/login', user);
  }

  registerUser(user: RegisterRequest): Observable<any> {
    return this.httpClient.post(this.baseLoginUrl + '/register', user);
  }
}
