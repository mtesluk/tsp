import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AuthComponent} from './auth/auth.component';
import {AuthGuard} from './auth/auth.guard';
import { SidebarComponent } from './sidebar/sidebar.component';


const routes: Routes = [
  {
    path: 'login',
    component: AuthComponent,
  },
  {
    path: '',
    component: SidebarComponent,
    canActivate: [AuthGuard]
  },
  {
    path: '**',
    redirectTo: ''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
