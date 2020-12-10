import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ReportService } from 'src/app/services/report.service';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-new-report-dialog',
  templateUrl: './new-report-dialog.component.html',
  styleUrls: ['./new-report-dialog.component.css']
})
export class NewReportDialogComponent implements OnInit {
  reportForm = new FormGroup({
    description: new FormControl('', Validators.required),
    city: new FormControl('', Validators.required),
    street: new FormControl('', Validators.required),
    streetNumber: new FormControl('', Validators.required),
    latitude: new FormControl('', Validators.required),
    longitude: new FormControl('', Validators.required),
  });
  fileUpload: File;

  constructor(private _service: ReportService,
    public dialogRef: MatDialogRef<NewReportDialogComponent>,) { }

  ngOnInit(): void {
    console.log(this.reportForm)
  }

  onSubmit(event) {
    event.preventDefault();
    if (this.reportForm.valid) {
      this._service.save({...this.reportForm.value, reportStatus: 'NEW', accoundId: 1}, this.fileUpload).subscribe(ele => {
        this.dialogRef.close();
      })
    }
  }

  onImageChange(e) {
    this.fileUpload = e.target.files[0];
  }

}
