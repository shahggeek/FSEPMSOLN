import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { UserComponent } from './user/user.component';
import { TaskComponent } from './task/task.component';
import { ProjectComponent } from './project/project.component';
import { HttpClientModule } from '@angular/common/http';
import { SortDirective } from './shared/sortdata.directive';
import { UserFilterPipe } from './shared/userfilter.pipe';
import { ViewTaskComponent } from './view-task/view-task.component';
import { TaskFilterPipe } from './shared/taskfilter.pipe';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    UserComponent,
    TaskComponent,
    ProjectComponent,
    SortDirective,
    UserFilterPipe,
    TaskFilterPipe,
    ViewTaskComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
