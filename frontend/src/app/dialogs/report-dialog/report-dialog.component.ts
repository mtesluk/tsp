import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {Report} from 'src/app/interfaces/report.interface';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {switchMap, tap} from 'rxjs/operators';

@Component({
  selector: 'app-report-dialog',
  templateUrl: './report-dialog.component.html',
  styleUrls: ['./report-dialog.component.css']
})
export class ReportDialogComponent implements OnInit {
  // link = 'http://mateusz.tesluk.pl/BLOG_20_0.jpg';
  link = '';
  imageArr = [
    'https://leszno.pl/photos/gal/p_38831_1.jpg',
    'https://i.grupamedio.pl/bff933899a545a46dbbbe3113f53d6ef/n/500/0',
    'https://www.portalwrc.pl/images/news/13014.jpg',
  ];
  image: any;

  constructor(@Inject(MAT_DIALOG_DATA) public data: Report,
              private _http: HttpClient) {
  }

  ngOnInit(): void {

    const index = Math.floor(Math.random() * (2 - 0 + 1));
    this.link = environment.url.report + '/' + this.data.id + '/image';
  }


}
