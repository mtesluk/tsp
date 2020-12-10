import { NgModule } from '@angular/core';
import { MatSidenavModule } from '@angular/material/sidenav';
import {MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatListModule} from '@angular/material/list';
import {MatIconModule} from '@angular/material/icon';


@NgModule({
  declarations: [],
  imports: [
    MatSidenavModule,
    MatDialogModule,
    MatFormFieldModule,
    MatListModule,
    MatIconModule,
  ],
  exports: [
    MatSidenavModule,
    MatDialogModule,
    MatFormFieldModule,
    MatListModule,
    MatIconModule,
  ],
})
export class MaterialModule { }
