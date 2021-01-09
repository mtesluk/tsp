import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {MaterialModule} from './material.module';
import {AppRoutingModule} from './app-routing.module';
import {SidebarComponent} from './sidebar/sidebar.component';
import {MapComponent} from './map/map.component';
import {NewReportDialogComponent} from './dialogs/new-report-dialog/new-report-dialog.component';
import {AccountDialogComponent} from './dialogs/account-dialog/account-dialog.component';
import {ReportsDialogComponent} from './dialogs/reports-dialog/reports-dialog.component';
import {AuthInterceptorService} from './auth/auth-interceptor.service';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReportDialogComponent } from './dialogs/report-dialog/report-dialog.component';
import {AuthComponent} from './auth/auth.component';
import {HttpClientModule} from '@angular/common/http';
import {MatTableModule} from '@angular/material/table';
import {CommonModule} from '@angular/common';
import { ErrorInterceptorService } from './auth/error-interceptor.service';
import { SecuredImageComponent } from './shared/component/secured-image-component/secured-image.component';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    MapComponent,
    SidebarComponent,
    AuthComponent,
    ReportsDialogComponent,
    NewReportDialogComponent,
    ReportDialogComponent,
    SecuredImageComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    MaterialModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatTableModule,
    BrowserAnimationsModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptorService,
      multi: true
    },
  ],
  entryComponents: [
    NewReportDialogComponent,
    AccountDialogComponent,
    ReportsDialogComponent,
    ReportDialogComponent,
    SecuredImageComponent,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
