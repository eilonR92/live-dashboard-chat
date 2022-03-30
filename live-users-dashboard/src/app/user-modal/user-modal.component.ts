import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {InfoForm} from "../info-form";

@Component({
  selector: 'app-user-modal',
  templateUrl: './user-modal.component.html',
  styleUrls: ['./user-modal.component.css']
})
export class UserModalComponent implements OnInit {

  @Input() user!: InfoForm
  @Input() show = false
  @Output() newCloseModalEvent = new EventEmitter<boolean>()

  constructor() { }

  ngOnInit(): void {
  }

  onCloseModal(){
    this.show = false
    this.newCloseModalEvent.emit(false)
  }

}
