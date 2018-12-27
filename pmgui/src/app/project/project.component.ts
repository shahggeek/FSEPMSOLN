import { Component, OnInit } from '@angular/core';
import { ProjectRestService } from '../shared/project-rest.service';
import { Project } from '../model/project.model';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  projects : Project [] = [];

  constructor(private projectRestService : ProjectRestService) { }

  ngOnInit() {
    this.getAllProjects();
  }

  getAllProjects(){
    this.projectRestService.getAllProjects().subscribe(
      (projects : any[]) => this.projects = projects,
      (error) => console.log(error)
    );
  }

}
