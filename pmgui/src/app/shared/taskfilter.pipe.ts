import { Pipe, PipeTransform, Injectable } from '@angular/core';
import { User } from '../model/user.model';
import { Task } from '../model/task.model';

@Pipe({
    name: 'taskfilter',
})
@Injectable()
export class TaskFilterPipe implements PipeTransform {
    transform(items: Task[], args: string): any {
        if (!items || !args) {
            return items;
        }
        return items.filter(item => item.taskName.toLowerCase().indexOf(args.toLowerCase()) !== -1);
    }
}