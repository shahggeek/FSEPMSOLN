import { Component, OnInit, ViewChild, ViewContainerRef, TemplateRef } from '@angular/core';
import { Task } from '../model/task.model';
import { TaskRestService } from '../shared/task-rest.service';
import { UserRestService } from '../shared/user-rest.service';
import { ProjectRestService } from '../shared/project-rest.service';
import { User } from '../model/user.model';
import { ParentTaskService } from '../shared/parent-task.service';
import { ParentTask } from '../model/parenttask.model';
import { NgForm } from '@angular/forms';
import { Project } from '../model/project.model';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  tasks : Task [] = [];
  users : User [] = [];
  parenttasks : ParentTask [] = [];
  projects : Project [] = [];
  isParent = false;
  selected: Task;

  task: Task = { taskId:0,  taskName: '', startDate: '', endDate:'', priority:0, status:'Open',
  project:{ projectId:0,  projectName: '', startDate: '', endDate:'', priority:0,  user: { userId: 0, firstName: '', lastName : '', employeeId : 0 , projectId:0, taskId:0}, tasks: [] },
  parentTask:{parentId:0, parentTaskName:''},
  user: { userId: 0, firstName: '', lastName : '', employeeId : 0 , projectId:0, taskId:0} };
  tomorrow : Date = new Date();
  today : Date = new Date();
  parentTask: ParentTask = {parentId:0, parentTaskName:''};
  error:any={isError:false,errorMessage:''};

  @ViewChild("outputAllTasks", {read: ViewContainerRef}) outputAllTasksRef: ViewContainerRef;
  
  
  @ViewChild('displayTmpl') displayTmpl: TemplateRef<any>;
  @ViewChild('editTmpl') editTmpl: TemplateRef<any>;
  @ViewChild('taskstable', {read: TemplateRef}) taskstable: TemplateRef<any>;
  
  
  constructor(private taskRestService : TaskRestService, 
    private userRestService : UserRestService,
    private projectRestService : ProjectRestService,
    private parentTaskRestService : ParentTaskService) { }

  ngOnInit() {
    this.getAllTasks();
    this.getAllProjects();
    this.getAllUsers();
    this.getAllParentTasks();
    this.setDefaultDates();
  }

  private  setDefaultDates(){
    this.tomorrow  = new Date();
    this.today = new Date();
    this.tomorrow.setDate(this.today.getDate() + 1);
    this.task.startDate = this.today.toISOString().slice(0,10);
    this.task.endDate = this.tomorrow.toISOString().slice(0,10);
  }

  private rerender() {
    this.getAllTasks();
    this.getAllParentTasks();
    this.outputAllTasksRef.clear();
  }

  onAddTask(form : NgForm){
    this.isParent = form.value.isParent;
    if(!this.isParent){
      this.taskRestService.addTask(this.task).subscribe(
        (response : Response ) => {
          this.rerender();
        },
        (error) =>  window.alert(error.message)
      );
    }else{
      this.parentTask.parentId = 0;
      this.parentTask.parentTaskName = form.value.taskName;
      this.parentTaskRestService.addTask(this.parentTask).subscribe(
        (response : Response ) => {
          this.rerender();
        },
        (error) =>  window.alert(error.message)
      );
    }
    form.resetForm();
    this.setDefaultDates();
  }

  getAllTasks(){
    this.taskRestService.getAllTasks().subscribe(
      (tasks : any[]) => this.tasks = tasks,
      (error) =>  window.alert(error.message)
    );
  }

  getAllUsers(){
    this.userRestService.getAllUsers().subscribe(
      (users : any[]) => this.users = users,
      (error) => window.alert(error.message)
    );
  }

  getAllParentTasks(){
    this.parentTaskRestService.getAllParentTasks().subscribe(
      (parenttasks : any[]) => this.parenttasks = parenttasks,
      (error) =>  window.alert(error.message)
    );
  }

  
  getAllProjects(){
    this.projectRestService.getAllProjects().subscribe(
      (projects : any[]) => this.projects = projects,
      (error) =>  window.alert(error.message)
    );
  }

  editTask(task : Task){
    this.selected = Object.assign({}, task);
  }

  deleteTask(taskId : number){
    this.taskRestService.deleteTask(taskId).subscribe(
      (response : Response ) => {
        this.rerender();
      },
      (error) =>  window.alert(error.message)
    );
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
        (error) =>  window.alert(error.message)
      );
    }
   
    this.resetTask();
  }

  resetTask() {
      this.selected = null;
  }
  
  resetForm(){

  }
  
  changeCheckbox(event : any, userId : number){
    if( event.target.checked){
      this.task.user.userId = userId;
    }else{
      this.task.user.userId = null;
    }
  }

  changeParentCheckbox(event : any, parentId : number){
    if( event.target.checked){
      this.task.parentTask.parentId = parentId;
    }else{
      this.task.parentTask.parentId = null;
    }
  }

  changeProjectCheckbox(event : any, projectId : number){
    if( event.target.checked){
      this.task.project.projectId = projectId;
    }else{
      this.task.project.projectId  = null;
    }
  }

  compareTwoDates(endDateRef : HTMLInputElement){
    if(new Date(this.task.endDate) < new Date(this.task.startDate)){
       this.error={isError:true,errorMessage:'End Date cant be before start date'};
    }else{
      this.error ={isError:false,errorMessage:''};
    }
  }
}
