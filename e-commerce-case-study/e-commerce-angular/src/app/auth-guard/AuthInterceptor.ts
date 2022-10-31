import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from '../services/api.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private api: ApiService) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

      if(this.api.getToken()!=null){
        const authReq = req.clone({
            headers: req.headers.set('Authorization', `Bearer ${this.api.getToken()}`)
          });

          return next.handle(authReq);
      } else{
        return next.handle(req);
      }      
  }
}