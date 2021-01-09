import { Injectable } from '@angular/core';
import {HttpErrorResponse, HttpHandler, HttpHeaders, HttpInterceptor, HttpParams, HttpRequest} from '@angular/common/http';
import {AuthService} from './auth.service';
import {exhaustMap, take, tap} from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ErrorInterceptorService implements HttpInterceptor {
  constructor(
      private authService: AuthService,
      private router: Router,
      ) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    return this.authService.tokenBehaviourSubject.pipe(
      take(1),
      exhaustMap(user => {
        if (!user) {
          return next.handle(req);
        }
        const modifiedReq = req.clone({
          headers: new HttpHeaders({'Authorization': 'Bearer ' + user.token})
        });
        return next.handle(modifiedReq).pipe(
            tap(() => {},
            (err: any) => {
            if (err instanceof HttpErrorResponse) {
              if (err.status !== 401) {
               return;
              }
              this.router.navigate(['/login']);
            }
          })
        );
      })
    );
  }
}
