import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { PostComponent } from './post/post.component';
import { HomeComponent } from './home/home.component';
import {MatToolbar, MatToolbarModule} from "@angular/material/toolbar";
import {MatIcon, MatIconModule} from "@angular/material/icon";
import {MatMenu, MatMenuModule, MatMenuTrigger} from "@angular/material/menu";
import {MatDrawerContainer, MatSidenavModule} from "@angular/material/sidenav";
import {MatList, MatListItem, MatListModule} from "@angular/material/list";
import {MatSortModule} from "@angular/material/sort";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTableModule} from "@angular/material/table";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import { UserTemplateComponent } from './user-template/user-template.component';
import { CreatePostComponent } from './post/create-post/create-post.component';
import {MatDialogActions, MatDialogClose, MatDialogContent, MatDialogTitle} from "@angular/material/dialog";
import { PostDetailsComponent } from './post/post-details/post-details.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { SidebarComponent } from './sidebar/sidebar.component';
import {NgOptimizedImage} from "@angular/common";
import { CreateCommentComponent } from './post/post-details/create-comment/create-comment.component';
import {MatCheckbox, MatCheckboxModule} from "@angular/material/checkbox";
import { LoginComponent } from './login/login.component';
import {AppHttpInterceptor} from "./interceptors/app-http.interceptor";
import {AuthenticationGuard} from "./guards/authentication.guard";
import {AuthorizationGuard} from "./guards/authorization.guard";
import { RegisterComponent } from './register/register.component';
import { UserRoleComponent } from './user-role/user-role.component';
import {MatOption, MatSelect} from "@angular/material/select";
import {ScheduleModule} from "@syncfusion/ej2-angular-schedule";


@NgModule({
  declarations: [
    AppComponent,
    UserTemplateComponent,
    PostComponent,
    HomeComponent,
    CreatePostComponent,
    PostDetailsComponent,
    SidebarComponent,
    CreateCommentComponent,
    LoginComponent,
    RegisterComponent,
    UserRoleComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    MatSidenavModule,
    MatListModule,
    MatListItem,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatDialogContent,
    MatDialogClose,
    MatDialogActions,
    MatDialogTitle,
    NgOptimizedImage,
    MatCheckboxModule,
    FormsModule,
    MatSelect,
    MatOption,
    ScheduleModule

  ],
  providers: [
    provideAnimationsAsync(),
    {provide:HTTP_INTERCEPTORS, useClass:AppHttpInterceptor, multi:true},
    AuthenticationGuard,
    AuthorizationGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
