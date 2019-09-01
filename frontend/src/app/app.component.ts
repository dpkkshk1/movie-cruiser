import { Component } from '@angular/core';
import { AuthenticationService } from './modules/authentication/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl:'./app.component.html',
  styles: []
})
export class AppComponent {
  title = 'frontend';
  constructor(private authService:AuthenticationService,private route:Router){}  
  ngOnInit(){
  }
  Logout(){
    this.authService.deleteToken();
    this.route.navigate(['/login']);

  }
}
