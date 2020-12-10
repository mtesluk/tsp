import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {environment} from 'src/environments/environment';
import {Measurement} from '../interfaces/measurement.interface';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MeasurementService {

  constructor(private _http: HttpClient) {
  }

  fetchNearestMeasurement(lat: number, lng: number, maxDistanceKM: number): Observable<Measurement> {
    const params = new HttpParams()
      .set("latitude", lat.toString())
      .set("longitude", lng.toString())
      .set("maxDistanceKM", maxDistanceKM.toString());
    return this._http.get<Measurement>(environment.url.measurement + '/nearest', {params});
  }
}
