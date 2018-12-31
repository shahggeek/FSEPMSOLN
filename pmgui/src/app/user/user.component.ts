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
  selected: User;
  @ViewChild("outputAllUsers", {read: ViewContainerRef}) outputAllUsersRef: ViewContainerRef;
  @ViewChild("allUsers", {read: TemplateRef}) allUsersRef: TemplateRef<any>;

  @ViewChild('displayTmpl') displayTmpl: TemplateRef<any>;
  @ViewChild('editTmpl') editTmpl: TemplateRef<any>;
  @ViewChild('userstable', {read: TemplateRef}) userstable: TemplateRef<any>;
  

  constructor(private userRestService : UserRestService) { }

  ngOnInit() {
    this.getAllUsers();
  }

  private rerender() {
    this.getAllUsers();
    this.outputAllUsersRef.clear();
  }

  onAddUser(form : NgForm){
    console.log(form.value);
    this.userRestService.addUser(form.value).subscribe(
      (response : Response ) => {
        this.rerender();
      },
      (error) => console.log(error)
    );
    form.resetForm();
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
    console.log("Edit User"+user.firstName+" "+user.lastName);
    this.selected = Object.assign({}, user);
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

  getTemplate(user:User) {
    return this.selected && this.selected.userId == user.userId ? 
    this.editTmpl : this.displayTmpl;
  }

  saveUser(user:User) {
    console.log("Save User"+this.selected.userId+" "+this.selected.firstName+" "+this.selected.lastName);
    user.employeeId = this.selected.employeeId;
    user.firstName = this.selected.firstName;
    user.lastName = this.selected.lastName;
    this.userRestService.updateUser(user).subscribe(
      (response : Response ) => {
        this.rerender();
      },
      (error) => console.log(error)
    );
    this.resetUser();
  }

  resetUser() {
      this.selected = null;
  }
}
