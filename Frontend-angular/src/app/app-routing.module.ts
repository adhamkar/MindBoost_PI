import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PostComponent} from "./post/post.component";
import {UserTemplateComponent} from "./user-template/user-template.component";
import {PostDetailsComponent} from "./post/post-details/post-details.component";
import {HomeComponent} from "./home/home.component";
import {CreatePostComponent} from "./post/create-post/create-post.component";
import {CreateCommentComponent} from "./post/post-details/create-comment/create-comment.component";
import {LoginComponent} from "./login/login.component";
//import {AuthenticationGuard} from "./guards/authentication.guard";
//import {AuthorizationGuard} from "./guards/authorization.guard";
import {RegisterComponent} from "./register/register.component";
import {UserRoleComponent} from "./user-role/user-role.component";
import {MyPostsComponent} from "./post/my-posts/my-posts.component";
import {ProfileComponent} from "./profile/profile.component";
import {PlanningComponent} from "./planning/planning.component";
import {ToDoListComponent} from "./to-do-list/to-do-list.component";
import {NotesComponent} from "./notes/notes.component";
import {AuthenticationGuard} from "./guards/authentication.guard";
import {CalendarComponent} from "./calendar/calendar.component";
import {ChatbotComponent} from "./chatbot/chatbot.component";


const routes: Routes = [

  {path : "",redirectTo:"/login" ,pathMatch:"full"},
  { path: 'upcoming', component: ToDoListComponent },
  { path: 'today', component: ToDoListComponent },
  { path: 'calendar', component: CalendarComponent },

  {path : "login", component : LoginComponent},
  // {path : "home", component : HomeComponent},
  {path : "user_role", component : UserRoleComponent},
  {path : "signup", component : RegisterComponent},
  // {path : "All", component : UserTemplateComponent,children:[ {path : "home", component : HomeComponent}]},
  {path : "user", component : UserTemplateComponent ,canActivate:[AuthenticationGuard],children:[
      {path : "home", component : HomeComponent},
      {path : "chatbot", component : ChatbotComponent},
      {path : "posts", component : PostComponent},
      { path: 'posts/add', component: CreatePostComponent},
      { path: 'posts/:id', component: PostDetailsComponent,},
      { path: 'posts/:id/add', component: CreateCommentComponent },

      {path : "my-posts", component : MyPostsComponent},
      {path : "profile", component : ProfileComponent},
      {path : "planning", component : PlanningComponent},
      {path : "to-do-list", component : ToDoListComponent},
      {path : "notes", component : NotesComponent},

    ]},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
