import { Pipe, PipeTransform, Injectable } from '@angular/core';
import { ParentTask } from '../model/parenttask.model';

@Pipe({
    name: 'parenttaskfilter',
})
@Injectable()
export class ParentTaskFilterPipe implements PipeTransform {
    transform(items: ParentTask[], args: string): any {
        if (!items || !args) {
            return items;
        }
        return items.filter(item => item.parentTaskName.toLowerCase().indexOf(args.toLowerCase()) !== -1);
    }
}