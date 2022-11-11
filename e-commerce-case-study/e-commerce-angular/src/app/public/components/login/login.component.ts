import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
//import { AuthService } from '../../services/auth-service/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  loginForm: FormGroup = new FormGroup({
    email: new FormControl(null, [Validators.required, Validators.email]),
    //username: new FormControl(null, [Validators.required]),
    password: new FormControl(null, [Validators.required,Validators.pattern(
      /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*#?&^_-]).{8,}/
    )]),
  });

  constructor(
    //private authService: AuthService,
    private router: Router,
    private apiService: ApiService,
    private snackbar: MatSnackBar
  ) { }

  login() {
    if (!this.loginForm.valid) {
      return;
    }
    let user = {
      email: this.loginForm.value.email,
      password: this.loginForm.value.password,
    }
    this.apiService.login(user).
      subscribe(res => {
        if (res.status == "200" && res.userType == "CUSTOMER") {
          this.apiService.storeToken(res.authToken, "customer");
          this.router.navigate(['../../protected/']);
        } else if (res.status == "200" && res.userType == "ADMIN") {
          this.apiService.storeToken(res.authToken, "admin");
          this.router.navigate(['../../protected/']);
        }else if(res.status == "401"){
          this.snackbar.open("User is not verified", 'Close', {
            duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
          })
        }
      },
        err => {
          this.snackbar.open(err.error.message, 'Close', {
            duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
          })
          this.router.navigate(['/login']);
      });
  }

}
