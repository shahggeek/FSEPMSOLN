import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Task } from '../model/task.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskRestService {

  constructor(private httpClient: HttpClient) { }

  getAllTasks(){
    return this.httpClient.get<Task[]>('http://localhost:8080/tasks')
   .pipe(
       map(
    (tasks) => {
        return tasks;
    }
   ));
  }

  addTask(task :Task){
    return this.httpClient.post('http://localhost:8080/tasks',task);
  }

  
  updateTask(task :Task){
    return this.httpClient.put('http://localhost:8080/tasks/'+task.taskId,task);
  }

  deleteTask(taskId:number){
    return this.httpClient.delete('http://localhost:8080/tasks/'+taskId);
  }


}
