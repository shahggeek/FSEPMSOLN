import { User } from './user.model';
import { Task } from './task.model';

export class Project {
    public projectId:number;
    public projectName:string;
    public startDate:string;
    public endDate:string;
    public priority:number;
    public user:User;
    public tasks : Task[];

   
    constructor(projectId:number, projectName:string, startDate:string,endDate:string,
         priority:number, user:User, tasks : Task[]){
       this.projectId = projectId;
       this.projectName = projectName;
       this.startDate = startDate;
       this.endDate = endDate;
       this.priority = priority;
       this.user = user;
       this.tasks = tasks;
    }

}