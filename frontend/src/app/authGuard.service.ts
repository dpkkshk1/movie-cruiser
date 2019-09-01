import { Injectable } from "@angular/core";
import { CanActivate } from '@angular/router/src/interfaces';
import { Router } from '@angular/router';
import { AuthenticationService } from './modules/authentication/authentication.service';


@Injectable()
export class AuthguardService implements CanActivate{
    constructor(private router:Router,private authService:AuthenticationService){}
    canActivate(){
        if(!this.authService.isTokenExpired()){
            return true;
        }
        this.router.navigate(['login']);
        return false;
    }
}