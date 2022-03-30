import {Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import { User } from '../user';
import {InfoForm} from "../info-form";

@Component({
  selector: 'app-user-card',
  templateUrl: './user-card.component.html',
  styleUrls: ['./user-card.component.css']
})
export class UserCardComponent implements OnInit {

  @Input() user!: User
  public show: Boolean = false
  public date: string = ''
  @Output() newItemEvent = new EventEmitter<InfoForm>()

  constructor() {
  }

  ngOnInit(): void {
    const time: Date = new Date(this.user.lastLogIn)
    this.date = time.toLocaleString()
  }

  public onOpenModal(e: Event): void {
    e.preventDefault()
    const date:Date = new Date(this.user.regTime)
    const InfoForm = {
      userName: this.user.userName,
      regTime: date.toLocaleString(),
      logCount: this.user.logsCount
    }
    this.newItemEvent.emit(InfoForm)

  }
}


