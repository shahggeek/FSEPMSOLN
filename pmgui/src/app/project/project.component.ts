import { Component, OnInit, ViewChild, ViewContainerRef, TemplateRef } from '@angular/core';
import { ProjectRestService } from '../shared/project-rest.service';
import { Project } from '../model/project.model';
import { UserRestService } from '../shared/user-rest.service';
import { User } from '../model/user.model';
import { NgForm, FormControl } from '@angular/forms';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  projects : Project [] = [];
  users : User [] = [];
  selected: Project;
  project: Project = { projectId:0,  projectName: '', startDate: '' , endDate:'', priority:0,  user: { userId: 0, firstName: '', lastName : '', employeeId : 0 , projectId:0, taskId:0}, tasks:[] };
  tomorrow : Date = new Date();
  today : Date = new Date();
  error:any={isError:false,errorMessage:''};
  
  @ViewChild("outputAllProjects", {read: ViewContainerRef}) outputAllProjectsRef: ViewContainerRef;

  @ViewChild('displayTmpl') displayTmpl: TemplateRef<any>;
  @ViewChild('editTmpl') editTmpl: TemplateRef<any>;
  @ViewChild('userstable', {read: TemplateRef}) userstable: TemplateRef<any>;
  
  checkedUser : User;
  constructor(private projectRestService : ProjectRestService, private userRestService : UserRestService) { }

  ngOnInit() {
    this.getAllProjects();
    this.getAllUsers();
    this.setDefaultDates();
  }

  private  setDefaultDates(){
    this.tomorrow  = new Date();
    this.today = new Date();
    this.tomorrow.setDate(this.today.getDate() + 1);
    this.project.startDate = this.today.toISOString().slice(0,10);
    this.project.endDate = this.tomorrow.toISOString().slice(0,10);
  }

  private rerender() {
    this.getAllProjects();
    this.outputAllProjectsRef.clear();
  }


  onAddProject(form : NgForm){
    this.projectRestService.addProject(this.project).subscribe(
      (response : Response ) => {
        this.rerender();
      },
      (error) =>  window.alert(error.message)
    );
    form.resetForm();
    this.setDefaultDates();
  }

  getAllProjects(){
    this.projectRestService.getAllProjects().subscribe(
      (projects : any[]) => this.projects = projects,
      (error) =>  window.alert(error.message)
    );
  }

  getAllUsers(){
    this.userRestService.getAllUsers().subscribe(
      (users : any[]) => this.users = users,
      (error) =>  window.alert(error.message)
    );
  }

  editProject(project : Project){
    this.selected = Object.assign({}, project);
  }

  deleteProject(projectId : number){
    this.projectRestService.deleteProject(projectId).subscribe(
      (response : Response ) => {
        this.rerender();
      },
      (error) => window.alert(error.message)
    );
  }

  
  getTemplate(project:Project) {
    return this.selected && this.selected.projectId == project.projectId ? 
    this.editTmpl : this.displayTmpl;
  }

  saveUser(project:Project) {
    project.projectName = this.selected.projectName;
    project.startDate = this.selected.startDate;
    project.endDate = this.selected.endDate;
    project.priority = this.selected.priority;

    this.projectRestService.updateProject(project).subscribe(
      (response : Response ) => {
        this.rerender();
      },
      (error) => window.alert(error.message)
    );
    this.resetUser();
  }

  resetUser() {
      this.selected = null;
  }

  resetForm(){

  }

  submitModalValue(event : any){
    console.log(this.checkedUser.userId);
  }
  
  changeCheckbox(event : any, userId : number){
    if( event.target.checked){
      this.project.user.userId = userId;
    }else{
      this.project.user.userId = null;
    }
  }

  compareTwoDates(endDateRef : HTMLInputElement){
    if(new Date(this.project.endDate) < new Date(this.project.startDate)){
       this.error={isError:true,errorMessage:'End Date cant be before start date'};
    }else{
      this.error ={isError:false,errorMessage:''};
    }
  }

}