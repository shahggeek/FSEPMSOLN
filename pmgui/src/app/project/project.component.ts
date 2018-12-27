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
  
  @ViewChild("outputAllProjects", {read: ViewContainerRef}) outputAllProjectsRef: ViewContainerRef;
  @ViewChild("allProjects", {read: TemplateRef}) allProjectsRef: TemplateRef<any>;
  
  constructor(private projectRestService : ProjectRestService, private userRestService : UserRestService) { }

  ngOnInit() {
    this.getAllProjects();
  }

  ngAfterContentInit() {
    this.outputAllProjectsRef.createEmbeddedView(this.allProjectsRef);
  }

  private rerender() {
    this.getAllProjects();
    this.outputAllProjectsRef.clear();
    this.outputAllProjectsRef.createEmbeddedView(this.allProjectsRef);
  }


  onAddProject(form : NgForm){
    console.log(form.value);
    const value = form.value;
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
}
