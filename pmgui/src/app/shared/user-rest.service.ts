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

  addUser(user :User){
    console.log(user)
    return this.httpClient.post('http://localhost:8080/users',user);
  }

  
  updateUser(user :User){
    return this.httpClient.put('http://localhost:8080/users/'+user.userId,user);
  }

  deleteUser(userId:number){
    console.log("Delete user in rest"+userId);
    return this.httpClient.delete('http://localhost:8080/users/'+userId);
  }

}
