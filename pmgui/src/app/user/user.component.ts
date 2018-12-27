import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserRestService } from '../shared/user-rest.service';
import { User } from '../model/user.model';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users : User [] = [];

  constructor(private userRestService : UserRestService) { }

  ngOnInit() {
    this.getAllUsers();
  }

  onAddUser(form : NgForm){
    console.log(form.value);
    const value = form.value;
  }

  resetForm(){

  }

  getAllUsers(){
    this.userRestService.getAllUsers().subscribe(
      (users : any[]) => this.users = users,
      (error) => console.log(error)
    );
  }
}
