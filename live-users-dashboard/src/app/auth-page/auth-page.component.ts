import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {Router} from '@angular/router';
import {UserService} from "../user.service";


@Component({
  selector: 'app-auth-page',
  templateUrl: './auth-page.component.html',
  styleUrls: ['./auth-page.component.css'],
})
export class AuthPageComponent implements OnInit {

  email = new FormControl('', [Validators.required, Validators.email])
  password = new FormControl('', [Validators.required])
  error = null

  constructor(private userService: UserService, private router:Router) {

  }

  ngOnInit(): void {
  }

  userLogin() {
    const loginForm = {
      email: this.email.value,
      password:  this.password.value
    }
    this.userService.login(loginForm).subscribe(
      (token:string) =>{
        localStorage.setItem('token', JSON.stringify(token))
        this.router.navigate([''])
      },
      (error) => {
        alert("Invalid email or password")
      }
    )
  }

}
