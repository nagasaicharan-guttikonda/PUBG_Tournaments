import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable,BehaviorSubject } from 'rxjs';

import * as jwt_decode from 'jwt-decode';

export const TOKEN_NAME:string = "jwt_token";

@Injectable()
export class AuthenticationService {

  authServiceEndpoint:string = "http://localhost:8082/userservice";
  token:string;
  private loggedInState: BehaviorSubject<boolean>=new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient) { }

  get isLoggedIn(){
   if(localStorage.getItem(TOKEN_NAME)!=null){
     this.loggedInState.next(true);
    }
    return this.loggedInState.asObservable();
   }


  registerUser(newUser) {
    const url = `${this.authServiceEndpoint}/register`;
    return this.http.post(url, newUser, {responseType: 'text'});
  }

  loginUser(user):Observable<any> {
    const url = `${this.authServiceEndpoint}/login`;
    this.loggedInState.next(true);
    return this.http.post(url, user);
  }

  setToken(token:string,userId) {
    localStorage.setItem("userId",userId);
    return localStorage.setItem(TOKEN_NAME, token);
  }

  getToken() {
    return localStorage.getItem(TOKEN_NAME);
  }

  deleteToken() {
    this.loggedInState.next(false);
    return localStorage.removeItem(TOKEN_NAME);
  }


  getTokenExpirationDate(token: string) {
    const decoded = jwt_decode(token);
    if(decoded.exp === undefined) {
      return null;
    }
    const date = new Date(0);
    date.setUTCSeconds(decoded.exp);
    return date;
  }

  isTokenExpired(token?: string): boolean {
    if(!token) {
      token = this.getToken();
    }
    if(!token) {
      return true;
    }
    const date = this.getTokenExpirationDate(token);
    if(date === undefined || date === null) {
      return false;
    }
    return !(date.valueOf() > new Date().valueOf());
  }
}
