import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AuthenticationService } from './authentication.service';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
//import { SharedModule } from '../shared/shared.module';
import { AuthenticationRouterModule } from './authentication-router.module';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';

@NgModule({
  imports: [
    //SharedModule,
    CommonModule,
    FormsModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    HttpClientModule,
    AuthenticationRouterModule
  ],
  declarations: [
    RegisterComponent,
    LoginComponent
  ],
  providers: [AuthenticationService],
  exports: [
    AuthenticationRouterModule,LoginComponent,RegisterComponent
  ]
})
export class AuthenticationModule { }
