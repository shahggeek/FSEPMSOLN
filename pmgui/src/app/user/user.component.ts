import { Component, OnInit, ViewChild, TemplateRef, ViewContainerRef } from '@angular/core';
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

  @ViewChild("outputAllUsers", {read: ViewContainerRef}) outputAllUsersRef: ViewContainerRef;
  @ViewChild("allUsers", {read: TemplateRef}) allUsersRef: TemplateRef<any>;

  constructor(private userRestService : UserRestService) { }

  ngOnInit() {
    this.getAllUsers();
  }

  ngAfterContentInit() {
    this.outputAllUsersRef.createEmbeddedView(this.allUsersRef);
  }

  private rerender() {
    this.getAllUsers();
    this.outputAllUsersRef.clear();
    this.outputAllUsersRef.createEmbeddedView(this.allUsersRef);
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

  editUser(user : User){
    console.log("Edit User"+user);
  }

  deleteUser(userId : number){
    console.log("Delete User"+userId);
    this.userRestService.deleteUser(userId).subscribe(
      (response : Response ) => {
        this.rerender();
      },
      (error) => console.log(error)
    );
   
  }
}
