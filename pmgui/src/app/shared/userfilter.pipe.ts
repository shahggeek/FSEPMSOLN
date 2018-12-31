import { Pipe, PipeTransform, Injectable } from '@angular/core';
import { User } from '../model/user.model';

@Pipe({
    name: 'userfilter',
})
@Injectable()
export class UserFilterPipe implements PipeTransform {
    transform(items: User[], args: string): any {
        if (!items || !args) {
            return items;
        }
        return items.filter(item => item.firstName.toLowerCase().indexOf(args.toLowerCase()) !== -1);
    }
}