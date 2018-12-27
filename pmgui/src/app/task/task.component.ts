import { Component, OnInit } from '@angular/core';
import { Task } from '../model/task.model';
import { TaskRestService } from '../shared/task-rest.service';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  tasks : Task [] = [];

  constructor(private taskRestService : TaskRestService) { }

  ngOnInit() {
    this.getAllTasks();
  }

  getAllTasks(){
    this.taskRestService.getAllTasks().subscribe(
      (tasks : any[]) => this.tasks = tasks,
      (error) => console.log(error)
    );
  }

}
