import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as jwt_decoded from 'jwt-decode';
export const TOKEN_NAME:string ="jwt_token";
@Injectable({
    providedIn: 'root'
  })
  export class AuthenticationService {

    springEndPoint:string;
    token:string;
    constructor(private httpClient:HttpClient){
        this.springEndPoint = "http://localhost:8082/api/v1/userservice"
    }
    registerUser(newuser){
        const url = `${this.springEndPoint}/register`;
        return this.httpClient.post(url,newuser,{responseType:"text"});
    }
    loginUser(newUser){
        const url = `${this.springEndPoint}/login`;
        return this.httpClient.post(url,newUser);
    }
    setToken(token:string){
        return localStorage.setItem(TOKEN_NAME,token);
    }
    getToken(){
        return localStorage.getItem(TOKEN_NAME);
    }
    deleteToken(){
        return localStorage.removeItem(TOKEN_NAME);
    }
    getTokenExpirationDate(token:string):Date{
        const decoded  = jwt_decoded(token);
        console.log(decoded);
        if(decoded.exp == undefined)return null;
        const date = new Date(0);
        date.setUTCSeconds(decoded.exp);
        return date;
    }
    isTokenExpired(token?:string):boolean{
        if(!token) token = this.getToken();
        if(!token) return true;
        const date = this.getTokenExpirationDate(token);
        if(date == undefined || date == null) return false;
        return !(date.valueOf()>new Date().valueOf());
    }
  }