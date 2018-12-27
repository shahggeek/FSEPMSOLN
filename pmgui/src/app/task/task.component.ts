import { Component, OnInit, ViewChild, ViewContainerRef, TemplateRef } from '@angular/core';
import { Task } from '../model/task.model';
import { TaskRestService } from '../shared/task-rest.service';
import { UserRestService } from '../shared/user-rest.service';
import { ProjectRestService } from '../shared/project-rest.service';
import { User } from '../model/user.model';
import { ParentTaskService } from '../shared/parent-task.service';
import { ParentTask } from '../model/parenttask.model';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  tasks : Task [] = [];
  users : User [] = [];
  parenttasks : ParentTask [] = [];
  
  @ViewChild("outputAllTasks", {read: ViewContainerRef}) outputAllTasksRef: ViewContainerRef;
  @ViewChild("allTasks", {read: TemplateRef}) allTasksRef: TemplateRef<any>;
  
  constructor(private taskRestService : TaskRestService, 
    private userRestService : UserRestService,
    private projectRestService : ProjectRestService,
    private parentTaskRestService : ParentTaskService) { }

  ngOnInit() {
    this.getAllTasks();
  }

  ngAfterContentInit() {
    this.outputAllTasksRef.createEmbeddedView(this.allTasksRef);
  }

  private rerender() {
    this.getAllTasks();
    this.outputAllTasksRef.clear();
    this.outputAllTasksRef.createEmbeddedView(this.allTasksRef);
  }

  onAddTask(form : NgForm){
    console.log(form.value);
    const value = form.value;
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

  editTask(task : Task){
    console.log("Edit task "+task.taskId);
  }

  deleteTask(taskId : number){
    console.log("Delete task "+taskId);
    this.taskRestService.deleteTask(taskId).subscribe(
      (response : Response ) => {
        this.rerender();
      },
      (error) => console.log(error)
    );
  }
}
