import { Component, OnInit, ViewChild, ViewContainerRef, TemplateRef } from '@angular/core';
import { Task } from '../model/task.model';
import { User } from '../model/user.model';
import { ParentTask } from '../model/parenttask.model';
import { TaskRestService } from '../shared/task-rest.service';
import { UserRestService } from '../shared/user-rest.service';
import { ProjectRestService } from '../shared/project-rest.service';
import { ParentTaskService } from '../shared/parent-task.service';

@Component({
  selector: 'app-view-task',
  templateUrl: './view-task.component.html',
  styleUrls: ['./view-task.component.css']
})
export class ViewTaskComponent implements OnInit {

  constructor(private taskRestService : TaskRestService, 
    private userRestService : UserRestService,
    private projectRestService : ProjectRestService,
    private parentTaskRestService : ParentTaskService) { }

  ngOnInit() {
    this.getAllTasks();
  }

  private rerender() {
    this.getAllTasks();
    this.outputAllTasksRef.clear();
  }

  tasks : Task [] = [];
  users : User [] = [];
  parenttasks : ParentTask [] = [];
  isParent = false;
  selected: Task;

  @ViewChild("outputAllTasks", {read: ViewContainerRef}) outputAllTasksRef: ViewContainerRef;
  @ViewChild('displayTmpl') displayTmpl: TemplateRef<any>;
  @ViewChild('editTmpl') editTmpl: TemplateRef<any>;
  @ViewChild('taskstable', {read: TemplateRef}) taskstable: TemplateRef<any>;

  getAllTasks(){
    this.taskRestService.getAllTasks().subscribe(
      (tasks : any[]) => this.tasks = tasks,
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
    this.selected = Object.assign({}, task);
  }

  endTask(taskId : number){
    console.log("End task "+taskId);
  }

  
  getTemplate(task:Task) {
    return this.selected && this.selected.taskId == task.taskId ? 
    this.editTmpl : this.displayTmpl;
  }

  saveTask(task:Task) {
    task.taskName = this.selected.taskName;
    task.startDate = this.selected.startDate;
    task.endDate = this.selected.endDate;
    task.priority = this.selected.priority;
    task.status = this.selected.status;
    if(!this.isParent){
      this.taskRestService.updateTask(task).subscribe(
        (response : Response ) => {
          this.rerender();
        },
        (error) => console.log(error)
      );
    }
   
    this.resetTask();
  }

  resetTask() {
      this.selected = null;
  }

}
