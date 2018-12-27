import { Component, OnInit } from '@angular/core';
import { ProjectRestService } from '../shared/project-rest.service';
import { Project } from '../model/project.model';
import { UserRestService } from '../shared/user-rest.service';
import { User } from '../model/user.model';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  projects : Project [] = [];
  users : User [] = [];
  
  constructor(private projectRestService : ProjectRestService, private userRestService : UserRestService) { }

  ngOnInit() {
    this.getAllProjects();
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

}
