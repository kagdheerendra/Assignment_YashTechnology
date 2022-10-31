import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
//import { AuthService } from '../../services/auth-service/auth.service';
import { tap } from 'rxjs';
import { Router, UrlSerializer } from '@angular/router';
import { CustomValidators } from '../../validators/custom-validator';
import { ApiService } from 'src/app/services/api.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  registerForm = new FormGroup({
    email: new FormControl(null, [Validators.required, Validators.email]),
    username: new FormControl(null, [Validators.required, Validators.minLength(4), Validators.maxLength(20), Validators.pattern('^[a-zA-Z]+$')]),
    password: new FormControl(null, [Validators.required, Validators.pattern(
      /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*#?&^_-]).{8,}/
    ),]),
    passwordConfirm: new FormControl(null, [Validators.required])
  },
    // add custom Validators to the form, to make sure that password and passwordConfirm are equal
    { validators: CustomValidators.passwordsMatching }
  )

  constructor(
    private router: Router,
    private apiService: ApiService,
    private snackbar: MatSnackBar,
    //private authService: AuthService
  ) { }

  register() {
    if (!this.registerForm.valid) {
      return;
    }
    let user = {
      email: this.registerForm.value.email,
      userName: this.registerForm.value.username,
      password: this.registerForm.value.passwordConfirm,
      userType: "customer"
    }
    this.apiService.register(user).
      subscribe(res => {
        if (res.status == "400") {
          this.snackbar.open(res.status, 'Close', {
            duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
          });
        } else {
          this.snackbar.open(`User created successfully`, 'Close', {
            duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
          })
          this.router.navigate(['/login']);
        }
      },
        err => {
          alert("An error has occured, Please try again !!!");
        });
  }

}
