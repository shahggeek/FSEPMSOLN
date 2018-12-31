import { Component, OnInit, ViewChild, ViewContainerRef, TemplateRef } from '@angular/core';
import { ProjectRestService } from '../shared/project-rest.service';
import { Project } from '../model/project.model';
import { UserRestService } from '../shared/user-rest.service';
import { User } from '../model/user.model';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  projects : Project [] = [];
  users : User [] = [];
  selected: Project;
  project: Project = { projectId:0,  projectName: '', startDate: '', endDate:'', priority:0,  user: { userId: 0, firstName: '', lastName : '', employeeId : 0 , projectId:0, taskId:0} };

  @ViewChild("outputAllProjects", {read: ViewContainerRef}) outputAllProjectsRef: ViewContainerRef;
  @ViewChild("allProjects", {read: TemplateRef}) allProjectsRef: TemplateRef<any>;

  @ViewChild('displayTmpl') displayTmpl: TemplateRef<any>;
  @ViewChild('editTmpl') editTmpl: TemplateRef<any>;
  @ViewChild('userstable', {read: TemplateRef}) userstable: TemplateRef<any>;
  
  checkedUser : User;
  constructor(private projectRestService : ProjectRestService, private userRestService : UserRestService) { }

  ngOnInit() {
    this.getAllProjects();
    this.getAllUsers();
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
      (error) => console.log(error)
    );
    form.resetForm();
  }

  getAllProjects(){
    this.projectRestService.getAllProjects().subscribe(
      (projects : any[]) => this.projects = projects,
      (error) => console.log(error)
    );
  }

  getAllUsers(){
    this.userRestService.getAllUsers().subscribe(
      (users : any[]) => this.users = users,
      (error) => console.log(error)
    );
  }

  editProject(project : Project){
    console.log("Edit project "+project.projectId);
    this.selected = Object.assign({}, project);
  }

  deleteProject(projectId : number){
    console.log("Delete project "+projectId);
    this.projectRestService.deleteProject(projectId).subscribe(
      (response : Response ) => {
        this.rerender();
      },
      (error) => console.log(error)
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
      (error) => console.log(error)
    );
    this.resetUser();
  }

  resetUser() {
      this.selected = null;
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

}