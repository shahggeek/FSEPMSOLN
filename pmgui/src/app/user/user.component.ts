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
    this.userRestService.addUser(form.value).subscribe(
      (response : Response ) => {
        this.rerender();
      },
      (error) =>  window.alert(error.message)
    );
    form.resetForm();
  }

  resetForm(){

  }

  getAllUsers(){
    this.userRestService.getAllUsers().subscribe(
      (users : any[]) => this.users = users,
      (error) =>  window.alert(error.message)
    );
  }

  editUser(user : User){
    this.selected = Object.assign({}, user);
  }

  deleteUser(userId : number){
    this.userRestService.deleteUser(userId).subscribe(
      (response : Response ) => {
        this.rerender();
      },
      (error) =>  window.alert(error.message)
    );
  }

  getTemplate(user:User) {
    return this.selected && this.selected.userId == user.userId ? 
    this.editTmpl : this.displayTmpl;
  }

  updateUser(user:User) {
    user.employeeId = this.selected.employeeId;
    user.firstName = this.selected.firstName;
    user.lastName = this.selected.lastName;
    this.userRestService.updateUser(user).subscribe(
      (response : Response ) => {
        this.rerender();
      },
      (error) =>  window.alert(error.message)
    );
    this.resetUser();
  }

  resetUser() {
      this.selected = null;
  }
}
