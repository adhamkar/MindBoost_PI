import {Component, OnInit} from '@angular/core';
import {AuthService} from "./services/auth.service";
import {Router} from "@angular/router";
import { EventSettingsModel, DayService, WeekService, WorkWeekService, MonthService, AgendaService } from '@syncfusion/ej2-angular-schedule';


@Component({
  selector: 'app-root',
  providers: [DayService, WeekService, WorkWeekService, MonthService, AgendaService],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'frontend-ang-mindBoost';
constructor(private authService:AuthService,private router:Router) {
}
  ngOnInit(): void {
    this.router.navigateByUrl('/user/home');
    this.authService.LoadJwtTokenFromLocalStorage();
  }

}
