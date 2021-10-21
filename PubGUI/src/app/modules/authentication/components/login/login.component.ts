import { Component, OnInit } from '@angular/core';
import { User } from '../../user';
import { AuthenticationService } from '../../authentication.service';
import { Router } from '@angular/router';
import { AppComponent } from '../../../../app.component';
import { HttpErrorResponse } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'user-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user:User;

  constructor(private authService: AuthenticationService,private snackbar:MatSnackBar,private appComponent:AppComponent,private router: Router) {
    this.user= new User();
   }

  ngOnInit() {
  }

  loginUser() {
    
    this.authService.loginUser(this.user).subscribe(data => {
     
      if(data['token']) {
        this.authService.setToken(data['token'],this.user.userId);
       this.appComponent.ngOnInit();
        this.router.navigate(['/tournaments']);
      }
      
      
    },
    
    (error:HttpErrorResponse)=>{
      this.snackbar.open("Wrong credentials",'',{
        duration:1000
      });
    }

    );
  }
}
