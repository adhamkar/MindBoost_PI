import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PostComponent} from "./post/post.component";
import {UserTemplateComponent} from "./user-template/user-template.component";
import {PostDetailsComponent} from "./post/post-details/post-details.component";
import {HomeComponent} from "./home/home.component";
import {CreatePostComponent} from "./post/create-post/create-post.component";
import {CreateCommentComponent} from "./post/post-details/create-comment/create-comment.component";
import {LoginComponent} from "./login/login.component";
import {AuthenticationGuard} from "./guards/authentication.guard";
import {AuthorizationGuard} from "./guards/authorization.guard";
import {RegisterComponent} from "./register/register.component";
import {UserRoleComponent} from "./user-role/user-role.component";


const routes: Routes = [
  {path : "", redirectTo:"/login", pathMatch:"full"},

  {path : "login", component : LoginComponent},
  {path : "home", component : HomeComponent},
  {path : "user_role", component : UserRoleComponent},
  {path : "signup", component : RegisterComponent},
  {path : "user", component : UserTemplateComponent,canActivate :[AuthenticationGuard],children:[
      {path : "posts", component : PostComponent},
      { path: 'posts/add', component: CreatePostComponent,canActivate :[AuthorizationGuard]},
      { path: 'posts/:id', component: PostDetailsComponent,},
      { path: 'posts/:id/add', component: CreateCommentComponent },
    ]},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
