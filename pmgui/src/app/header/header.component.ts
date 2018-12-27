import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Output() linkSelected = new EventEmitter<string>();
  
  constructor() { }

  ngOnInit() {
  }

  private setSelection(selection:string){
    this.linkSelected.emit(selection);
  }
}
