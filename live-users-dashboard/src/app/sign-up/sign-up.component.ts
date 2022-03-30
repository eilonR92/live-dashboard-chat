import {Component, OnInit} from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import {Router} from "@angular/router";
import {UserService} from "../user.service";

@Component({
  selector: 'sign-up-page',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit{

  name = new FormControl("", Validators.required);
  email = new FormControl('', [Validators.required, Validators.email])
  username = new FormControl("", Validators.required);
  password = new FormControl("", Validators.required);


  constructor(private userService: UserService, private router:Router) {
  }

  ngOnInit(): void {
  }

  userSignup() {
    const SignupForm = {
      name: this.name.value,
      email: this.email.value,
      userName: this.username.value,
      password:  this.password.value
    }
    this.userService.signup(SignupForm).subscribe(
      (token:string) =>{
        localStorage.setItem('token', JSON.stringify(token))
        this.router.navigate([''])
      }
    )
  }
}
