import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { ParentTask } from '../model/parenttask.model';

@Injectable({
  providedIn: 'root'
})
export class ParentTaskService {

  constructor(private httpClient: HttpClient) { }

  getAllParentTasks(){
    return this.httpClient.get<ParentTask[]>('http://localhost:8080/parenttasks')
   .pipe(
       map(
    (parenttasks) => {
        return parenttasks;
    }
   ));
  }

  addTask(parenttask :ParentTask){
    return this.httpClient.post('http://localhost:8080/parenttasks',parenttask);
  }

}
