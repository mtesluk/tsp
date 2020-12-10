import { Component, OnInit } from '@angular/core';

import {MatTableModule} from '@angular/material/table';
import {ReportService} from '../../services/report.service';
import {Report} from '../../interfaces/report.interface';

@Component({
  selector: 'app-reports-dialog',
  templateUrl: './reports-dialog.component.html',
  styleUrls: ['./reports-dialog.component.css']
})



// export interface
export class ReportsDialogComponent implements OnInit {

  reports: Report[];

  rows: ReportShow[];

  columnHeaders = ['reportStatus', 'address', 'dateOfCreation', 'description'];

  constructor(private reportService: ReportService) { }

  ngOnInit(): void {
    this.reportService.fetchReports().subscribe(rep => {
      this.reports = rep;

      // console.log(rep);
      // rep.forEach(ob => {
      //   this.rows.push(new ReportShow(
      //     ob.reportStatus,
      //     ob.city + ob.street + ob.streetNumber,
      //     ob.createdDateTime,
      //     ob.description
      //   ));
      // });
      //
      // console.log(this.rows);

    });
  }
}

export class ReportShow {
  constructor(
    public reportStatus: string,
    public address: string,
    public date: string,
    public descritpion: string,
  ) {
  }
}
