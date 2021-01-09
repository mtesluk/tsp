import { HttpClient } from '@angular/common/http';
import { Component, Input, OnChanges, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/internal/operators/map';
import { switchMap } from 'rxjs/internal/operators/switchMap';
import { tap } from 'rxjs/internal/operators/tap';
import { finalize } from 'rxjs/internal/operators/finalize';

@Component({
  selector: 'app-secured-image',
  templateUrl: './secured-image.component.html',
  styleUrls: ['./secured-image.component.css']
})
export class SecuredImageComponent implements OnChanges, OnInit {
  
  @Input() src: string;
  src$: BehaviorSubject<string> = new BehaviorSubject("");
  dataUrl: Observable<any>;
  loading = false;
  
  ngOnChanges(): void {
    this.src$.next(this.src);
  }

  ngOnInit() {
    this.src$.next(this.src);
    this.dataUrl = this.src$.pipe(
      tap(() => {this.loading = true}),
      switchMap(url => this._loadImage(url)),
      tap(() => {this.loading = false})
    );
  }

  constructor(
    private httpClient: HttpClient,
    private domSanitizer: DomSanitizer,
  ) { }

  private _loadImage(url: string): Observable<any> {
    return this.httpClient.get(url, {responseType: 'blob'}).pipe(
      map(e => this.domSanitizer.bypassSecurityTrustUrl(URL.createObjectURL(e)))
    );
  }

}
