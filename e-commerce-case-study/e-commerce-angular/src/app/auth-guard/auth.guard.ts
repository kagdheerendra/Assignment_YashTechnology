import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { ApiService } from '../services/api.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private auth: ApiService, private router: Router) {
  }
  canActivate(): boolean {

    if(this.auth.isAuthenticated()){
      return true;
    }else {
      this.router.navigate(['/public']);
      return false;
    }
  }
  
}
