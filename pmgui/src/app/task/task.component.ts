import { Component, OnInit } from '@angular/core';
import { Task } from '../model/task.model';
import { TaskRestService } from '../shared/task-rest.service';
import { UserRestService } from '../shared/user-rest.service';
import { ProjectRestService } from '../shared/project-rest.service';
import { User } from '../model/user.model';
import { ParentTaskService } from '../shared/parent-task.service';
import { ParentTask } from '../model/parenttask.model';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  tasks : Task [] = [];
  users : User [] = [];
  parenttasks : ParentTask [] = [];
  
  constructor(private taskRestService : TaskRestService, 
    private userRestService : UserRestService,
    private projectRestService : ProjectRestService,
    private parentTaskRestService : ParentTaskService) { }

  ngOnInit() {
    this.getAllTasks();
  }

  getAllTasks(){
    this.taskRestService.getAllTasks().subscribe(
      (tasks : any[]) => this.tasks = tasks,
      (error) => console.log(error)
    );
  }

  getAllUsers(){
    this.userRestService.getAllUsers().subscribe(
      (users : any[]) => this.users = users,
      (error) => console.log(error)
    );
  }

  getAllParentTasks(){
    this.parentTaskRestService.getAllParentTasks().subscribe(
      (parenttasks : any[]) => this.parenttasks = parenttasks,
      (error) => console.log(error)
    );
  }
}
