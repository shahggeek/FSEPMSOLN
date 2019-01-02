import { Pipe, PipeTransform, Injectable } from '@angular/core';
import { User } from '../model/user.model';
import { Task } from '../model/task.model';

@Pipe({
    name: 'taskscount',
})
@Injectable()
export class TaskCountFilterPipe implements PipeTransform {
    transform(items: Task[], args: string): any {
        if (!items || !args) {
            return items;
        }
        return items.filter(item => item.status.toLowerCase().indexOf(args.toLowerCase()) !== -1);
    }
}