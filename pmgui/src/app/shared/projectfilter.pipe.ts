import { Pipe, PipeTransform, Injectable } from '@angular/core';
import { User } from '../model/user.model';
import { Task } from '../model/task.model';
import { Project } from '../model/project.model';

@Pipe({
    name: 'projectfilter',
})
@Injectable()
export class ProjectFilterPipe implements PipeTransform {
    transform(items: Project[], args: string): any {
        if (!items || !args) {
            return items;
        }
        return items.filter(item => item.projectName.toLowerCase().indexOf(args.toLowerCase()) !== -1);
    }
}