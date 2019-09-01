import { Component, OnInit } from '@angular/core';
import { User } from '../../user';
import { AuthenticationService } from '../../authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'authentication-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  newUser:User;
  constructor(private authService:AuthenticationService,private router:Router) {
    this.newUser = new User();
   }

  ngOnInit() {
  }
  registerUser(){
    console.log(this.newUser);
    this.authService.registerUser(this.newUser).subscribe((data)=>{
      console.log("data"+data);
      this.router.navigate(['login']);
    })
  }
  resetInput(){
    this.newUser =new User();
  }
}
