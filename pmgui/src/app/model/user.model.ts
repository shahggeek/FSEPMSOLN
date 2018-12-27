
export class User{
    public userId:number; 
    public firstName:string;
    public lastName:string;
    public employeeId:number;
    public projectId:number;
    public taskId:number;

    constructor(userId:number, firstName:string, lastName:string, employeeId:number, projectId:number, taskId:number){
       this.userId = userId;
       this.firstName = firstName;
       this.lastName = lastName;
       this.employeeId = employeeId;
       this.projectId = projectId;
       this.taskId = taskId;
    }
}