import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthPageComponent} from "./auth-page/auth-page.component";
import {UsersPageComponent} from "./users-page/users-page.component";
import {AuthGuard} from "./auth.guard";
import {SignUpComponent} from "./sign-up/sign-up.component";

const routes: Routes = [
  {
    path: '',
    component: UsersPageComponent,
    canActivate:[AuthGuard]
  },
  {
    path: 'login',
    component: AuthPageComponent,
  },
  {
    path: 'signup',
    component: SignUpComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
