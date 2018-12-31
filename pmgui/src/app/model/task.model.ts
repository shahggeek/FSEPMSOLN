import { Project } from './project.model';
import { ParentTask } from './parenttask.model';
import { User } from './user.model';

export class Task {
    public taskId:number;
    public taskName:string;
    public startDate:string;
    public endDate:string;
    public priority:number;
    public status:string;
    public project:Project;
    public parentTask:ParentTask;
    public user:User;

    constructor(taskId:number, taskName:string, startDate:string,endDate:string, priority:number, status:string, project:Project, parentTask:ParentTask, user:User){
       this.taskId = taskId;
       this.taskName = taskName;
       this.startDate = startDate;
       this.endDate = endDate;
       this.priority = priority;
       this.status = status;
       this.project = project;
       this.parentTask = parentTask;
       this.user = user;
    }


}