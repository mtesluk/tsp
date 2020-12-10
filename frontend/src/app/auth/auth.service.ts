import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {catchError, switchMap, tap} from 'rxjs/operators';
import {Token} from './token.model';
import {BehaviorSubject, Observable} from 'rxjs';
import {environment} from '../../environments/environment';

export interface Account {
  id: number;
  email: string;
  username: string;
  role: string;
  points: number;
}

export interface AuthResponseData {
  token: string;
  expiresIn: string;
  accountId: number;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  tokenBehaviourSubject = new BehaviorSubject<Token>(null);
  userSubject = new BehaviorSubject<Account>(null);
  private tokenExpirationTimer: any;

  constructor(private http: HttpClient, private router: Router) { }

  signup(email: string, username: string, password: string, city: string): Observable<any> {
    return this.http.post<AuthResponseData>(environment.url.singUp,
        {
          email: email,
          password: password,
          username: username,
          city: city,
        }
      )
      .pipe(
        // todo
        catchError(err => err.subscribe(error => console.log(error))),
        tap(resData => {
          this.handleAuthentication(resData.token, +resData.expiresIn, +resData.accoundId);
        })
      );
  }

  login(username: string, password: string): Observable<any> {
    return this.http
      .post<AuthResponseData>(
        environment.url.login,
        {
          username: username,
          password: password
        }
      )
      .pipe(
        // catchError(err => err.subscribe(error => console.log(error))),
        tap(resData => {
          this.handleAuthentication(resData.token, +resData.expiresIn, +resData.accountId);
        }),
        switchMap(resData => this.http.get<Account>(
              environment.url.user + '/' + resData.accountId
            )),
        tap(account => this.userSubject.next(account))
      );
  }

  autoLogin(): void {
    const token: {
      token: string;
      tokenExpirationDate: string;
      id: number;
    } = JSON.parse(localStorage.getItem('token'));

    if (!token) {
      return;
    }

    const loadedToken = new Token(
      token.token,
      new Date(token.tokenExpirationDate),
      token.id,
    );

    if (token.token) {
      this.tokenBehaviourSubject.next(loadedToken);
      const expirationDuration =
        new Date(token.tokenExpirationDate).getTime() -
        new Date().getTime();
      this.autoLogout(expirationDuration);
      this.http.get<Account>(environment.url.user + '/' + token.id).subscribe((account: Account) => {
        this.userSubject.next(account)
      })
    }
  }

  logout(): void {
    this.tokenBehaviourSubject.next(null);
    this.router.navigate(['/login']);
    localStorage.removeItem('token');
    if (this.tokenExpirationTimer) {
      clearTimeout(this.tokenExpirationTimer);
    }
    this.tokenExpirationTimer = null;
  }

  autoLogout(expirationDuration: number): void {
    this.tokenExpirationTimer = setTimeout(() => {
      this.logout();
    }, expirationDuration);
  }

  private handleAuthentication(token: string, expiresIn: number, accountId: number): void {
    const expirationDate = new Date(new Date().getTime() + expiresIn * 1000);
    const tokenObj = new Token(token, expirationDate, accountId);
    this.tokenBehaviourSubject.next(tokenObj);
    this.autoLogout(expiresIn * 1000);
    localStorage.setItem('token', JSON.stringify(tokenObj));
  }
}
