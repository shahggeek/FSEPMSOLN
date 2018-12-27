
export class ParentTask {
    public parentId:number;
    public parentTaskName:string;
    

    constructor(parentId:number, parentTaskName:string){
       this.parentId = parentId;
       this.parentTaskName = parentTaskName;
    }

}