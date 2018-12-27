import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Project } from '../model/project.model';

@Injectable({
  providedIn: 'root'
})
export class ProjectRestService {

  constructor(private httpClient: HttpClient) { }

  getAllProjects(){
    return this.httpClient.get<Project[]>('http://localhost:8080/projects')
   .pipe(
       map(
    (projects) => {
        return projects;
    }
   ));
  }

  
  addProject(project :Project){
    return this.httpClient.post('http://localhost:8080/projects',project);
  }

  
  updateProject(project :Project){
    return this.httpClient.put('http://localhost:8080/projects/'+project.projectId,project);
  }

  deleteProject(projectId:number){
    return this.httpClient.delete('http://localhost:8080/projects/'+projectId);
  }
}
