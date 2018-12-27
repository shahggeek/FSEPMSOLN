export class Project {
    public projectId:number;
    public projectName:string;
    public startDate:string;
    public endDate:string;
    public priority:number;

   
    constructor(projectId:number, projectName:string, startDate:string,endDate:string, priority:number){
       this.projectId = projectId;
       this.projectName = projectName;
       this.startDate = startDate;
       this.endDate = endDate;
       this.priority = priority;
    }

}