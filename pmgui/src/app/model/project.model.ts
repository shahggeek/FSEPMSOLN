import { User } from './user.model';

export class Project {
    public projectId:number;
    public projectName:string;
    public startDate:string;
    public endDate:string;
    public priority:number;
    public user:User;

   
    constructor(projectId:number, projectName:string, startDate:string,endDate:string, priority:number, user:User){
       this.projectId = projectId;
       this.projectName = projectName;
       this.startDate = startDate;
       this.endDate = endDate;
       this.priority = priority;
       this.user = user;
    }

}