import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {ReportsDialogComponent} from '../dialogs/reports-dialog/reports-dialog.component';
import { NewReportDialogComponent } from '../dialogs/new-report-dialog/new-report-dialog.component';
import {AuthService} from '../auth/auth.service';
import { AccountDialogComponent } from '../dialogs/account-dialog/account-dialog.component';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  constructor(private dialog: MatDialog,
              private authService: AuthService) {}

  ngOnInit(): void {
  }

  openReportsDialog(): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    this.dialog.open(ReportsDialogComponent, dialogConfig);
  }

  openNew() {
    const dialog = this.dialog.open(NewReportDialogComponent);
    dialog.afterClosed().subscribe(() => {
      // tu dodac odswiezanie sie wszystkich raportow
    })
  }

  openReports() {
    const dialog = this.dialog.open(ReportsDialogComponent, {
      width: '80%',
    });
  }

  openUser() {
    const dialog = this.dialog.open(AccountDialogComponent, {
      height: '70%',
      width: '70%',
    });
  }

  logout() {
    this.authService.logout();
  }

}
