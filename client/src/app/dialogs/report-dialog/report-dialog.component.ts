import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {Report} from 'src/app/interfaces/report.interface';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';


@Component({
  selector: 'app-report-dialog',
  templateUrl: './report-dialog.component.html',
  styleUrls: ['./report-dialog.component.css']
})
export class ReportDialogComponent implements OnInit {
  link = '';

  constructor(@Inject(MAT_DIALOG_DATA) public data: Report,
              private _http: HttpClient) {
  }

  ngOnInit(): void {
    this.link = environment.url.report + '/' + this.data.id + '/image';
  }


}
