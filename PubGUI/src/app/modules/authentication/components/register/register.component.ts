import { Component, OnInit } from '@angular/core';
import { User } from '../../user';
import { AuthenticationService } from '../../authentication.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'user-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser: User = new User();
  constructor(private authService: AuthenticationService,private snackBar:MatSnackBar,
              private router: Router) { }

  ngOnInit() {
  }

  registerUser() {
    this.authService.registerUser(this.newUser).subscribe(data => {
    this.router.navigate(['/login']);
    },
    (error:HttpErrorResponse)=>{
      this.snackBar.open("UserId Already Exists",'',{
        duration:1000
      });
    }
    );
    }
    
}
