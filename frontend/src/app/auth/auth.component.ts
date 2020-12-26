import {Component, ComponentFactoryResolver, OnInit} from '@angular/core';
import {AuthResponseData, AuthService} from './auth.service';
import {Router} from '@angular/router';
import {NgForm} from '@angular/forms';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
  isLogin = true;
  error = false;
  loading = false;

  constructor(
    private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm): void {
    if (!form.valid) {
      return;
    }

    // const user = new Token(form.value.email, password )
    const email = form.value.email;
    const password = form.value.password;
    const username = form.value.username;
    const city = form.value.city;

    this.loading = true;

    let authObs: Observable<AuthResponseData>;
    if (this.isLogin) {
      authObs = this.authService.login(username, password);
    } else {
      authObs = this.authService.signup(email, username, password, city);
    }

    authObs.subscribe(
      resData => {
        console.log(resData);
        this.router.navigate(['/']);
      },
      errorMessage => {
        console.log(errorMessage);
        this.error = errorMessage;
        this.error = true;
      },
      () => {
        this.loading = false;
      }
    );

    form.reset();
  }

  switchMode(): void {
    this.isLogin = !this.isLogin;
  }

}
