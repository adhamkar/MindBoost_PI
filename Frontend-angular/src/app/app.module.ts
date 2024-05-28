import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { PostComponent } from './post/post.component';
import { HomeComponent } from './home/home.component';
import {MatToolbar, MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
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
import {
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogModule,
  MatDialogTitle
} from "@angular/material/dialog";
import { PostDetailsComponent } from './post/post-details/post-details.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { SidebarComponent } from './sidebar/sidebar.component';
import {CommonModule, NgOptimizedImage} from "@angular/common";
import { CreateCommentComponent } from './post/post-details/create-comment/create-comment.component';
import {MatCheckbox, MatCheckboxModule} from "@angular/material/checkbox";
import { LoginComponent } from './login/login.component';
// import {AppHttpInterceptor} from "./interceptors/app-http.interceptor";
//import {AuthenticationGuard} from "./guards/authentication.guard";
// import {AuthorizationGuard} from "./guards/authorization.guard";
import { RegisterComponent } from './register/register.component';
import { UserRoleComponent } from './user-role/user-role.component';
import {MatOption, MatSelect, MatSelectModule} from "@angular/material/select";
import {RecurrenceEditorModule, ScheduleModule} from "@syncfusion/ej2-angular-schedule";
import {ProfileComponent} from "./profile/profile.component";
import {NotesComponent} from "./notes/notes.component";
import {MyPostsComponent} from "./post/my-posts/my-posts.component";
import {EditProfileComponent} from "./profile/edit-profile/edit-profile.component";
import {PlanningComponent} from "./planning/planning.component";
import {ToDoListComponent} from "./to-do-list/to-do-list.component";
import {MatNativeDateModule, MatOptionModule} from "@angular/material/core";
import {NgToastModule} from "ng-angular-popup";
//import {NgConfirmModule} from "ng-confirm-box";
import {MatTab, MatTabsModule} from "@angular/material/tabs";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {AppHttpInterceptor} from "./interceptors/app-http.interceptor";
import {AuthenticationGuard} from "./guards/authentication.guard";
import {AuthorizationGuard} from "./guards/authorization.guard";
import { EditPostComponent } from './post/edit-post/edit-post.component';
import { DatePipe } from '@angular/common';
import {MatGridListModule} from "@angular/material/grid-list";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {ChatbotComponent} from "./chatbot/chatbot.component";
import {CalendarComponent} from "./calendar/calendar.component";
import { RouterModule } from '@angular/router';
import { EditNoteComponent } from './notes/edit-note/edit-note.component';
import { ManageTaskComponent } from './manage-task/manage-task.component';
import { TaskDetailsComponent } from './task-details/task-details.component';
import { TaskSchedulerComponent } from './task-scheduler/task-scheduler.component';
import { AddTherapistComponent } from './add-therapist/add-therapist.component';
import { CallComponent } from './call/call.component';
import { NeedHelpComponent } from './need-help/need-help.component';
import { ReservationSessionComponent } from './reservation-session/reservation-session.component';
import { SessionByPatientComponent } from './session-by-patient/session-by-patient.component';
import { PatientsComponent } from './patients/patients.component';
import {MatRadioButton} from "@angular/material/radio";


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
    ProfileComponent,
    NotesComponent,
    MyPostsComponent,
    EditProfileComponent,
    PlanningComponent,
    ToDoListComponent,
    LoginComponent,
    RegisterComponent,
    UserRoleComponent,
    CalendarComponent,
    ChatbotComponent,
    EditPostComponent,
    EditNoteComponent,
    ManageTaskComponent,
    TaskDetailsComponent,
    TaskSchedulerComponent,
    AddTherapistComponent,
    CallComponent,
    NeedHelpComponent,
    ReservationSessionComponent,
    SessionByPatientComponent,
    PatientsComponent,





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
        MatSortModule,
        NgToastModule,
        //NgConfirmModule,
        MatSelectModule,
        MatOptionModule,
        ScheduleModule,
        RecurrenceEditorModule,
        MatTabsModule,
        FontAwesomeModule,
        CommonModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatGridListModule,
        RouterModule,
        MatDialogModule,
        MatRadioButton

    ],
  providers: [
    provideAnimationsAsync(),
    {provide:HTTP_INTERCEPTORS, useClass:AppHttpInterceptor, multi:true},
    AuthenticationGuard,
    AuthorizationGuard,
   DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
