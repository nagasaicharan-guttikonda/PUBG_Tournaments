import { Component } from '@angular/core';
import { AuthenticationService } from './modules/authentication/authentication.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'PubGUI';
  loggedInState:Observable<Boolean>
  userName:any;

  constructor(private auth:AuthenticationService,private routes:Router){}


  ngOnInit(){
  this.loggedInState=this.auth.isLoggedIn;

   if(localStorage.getItem("userId")){
     this.userName=localStorage.getItem("userId");
    }
  }

  logout()
{
 this.auth.deleteToken();
  this.routes.navigate(['/login']);
}

}
