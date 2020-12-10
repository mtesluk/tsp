import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth/auth.service';
import { Account } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-account-dialog',
  templateUrl: './account-dialog.component.html',
  styleUrls: ['./account-dialog.component.css']
})
export class AccountDialogComponent implements OnInit {
  letter: string = "";
  data: Account;

  constructor(private _authSevice: AuthService) { }

  ngOnInit(): void {
    this._authSevice.userSubject.subscribe((user: Account) => {
      this.letter = user.username.slice(0, 1);
      this.data = user;
      console.log(user)
    });
  }

}
