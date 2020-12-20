import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Report } from '../interfaces/report.interface';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private _http: HttpClient) { }

  fetchReports(): Observable<Report[]> {
    return this._http.get<Report[]>(environment.url.report);
  }

  save(data, file: File) {
    return this._http.post(environment.url.report, data).pipe(
      switchMap((res: Report) => {
        let formData = new FormData();
        formData.append('file', file);
        return this._http.post(`${environment.url.report}/${res.id}/image`, formData)
      }));
  }

}
