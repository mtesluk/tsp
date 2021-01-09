import {Component, OnInit} from '@angular/core';
import {AuthService} from './auth/auth.service';
import { ReportService } from './services/report.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'tsp-app';

  constructor(private authService: AuthService,
              private reportService: ReportService,
    ) {
  }


  ngOnInit(): void {
    this.authService.autoLogin();
  }
}
