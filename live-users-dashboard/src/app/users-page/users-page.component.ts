import {Component, OnInit} from '@angular/core';
import {User} from "../user";
import {UserService} from "../user.service";
import {Router} from "@angular/router";
import {InfoForm} from "../info-form";

@Component({
  selector: 'app-users-page',
  templateUrl: './users-page.component.html',
  styleUrls: ['./users-page.component.css']
})
export class UsersPageComponent implements OnInit {

  public users: User[] = [];
  public userName: string = ''
  public showModal: boolean = false
  public userInModal!: InfoForm
  public id: number = 0

  constructor(private userService: UserService, private router: Router) {
    this.updateUsers();
    const userJson = localStorage.getItem('token');
    this.userName = userJson !== null ? JSON.parse(userJson).userName : '';

  }

  ngOnInit() {
    this.id = setInterval(() => {
      this.updateUsers()
    }, 5000);
  }

  ngOnDestroy() {
    clearInterval(this.id)
  }

  private updateUsers() {
    this.userService.getUsers().subscribe(
      (users) => {
        this.users = users
      }
    )
  }


  getUserData(data: InfoForm){
    console.log(data)
    this.userInModal = data;
    this.showModal = true
  }

  closeModal(data: boolean){
    this.userInModal = null!
    this.showModal = data
  }

  public logout(){
    localStorage.removeItem('token')
    this.userService.logout(this.userName).subscribe()
    this.router.navigate(['login'])
  }


}
