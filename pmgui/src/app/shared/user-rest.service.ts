import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserRestService {

  constructor(private httpClient: HttpClient) { }

  getAllUsers(){
    return this.httpClient.get<User[]>('http://localhost:8080/users')
   .pipe(
       map(
    (users) => {
        return users;
    }
   ));
  }
}
