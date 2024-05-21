import { Component } from '@angular/core';
import {EventSettingsModel} from "@syncfusion/ej2-angular-schedule";

@Component({
  selector: 'app-planning',
  templateUrl: './planning.component.html',
  styleUrl: './planning.component.css'
})
export class PlanningComponent {

  public selectedDate: Date = new Date(2024, 1, 12);
  public eventSettings: EventSettingsModel = {
    dataSource: [{
      EndTime: new Date(2024, 1, 12, 14, 0),
      StartTime: new Date(2024, 1, 12, 9, 0),
      Subject: 'Environment Day'
    }, {
      EndTime: new Date(2024, 1, 13, 14, 0),
      StartTime: new Date(2024, 1, 13, 9, 0),
      Subject: 'Happiness Day'
    }, {
      EndTime: new Date(2024, 1, 14, 14, 0),
      StartTime: new Date(2024, 1, 14, 9, 0),
      Subject: 'Tourism Day'
    }]
  };

}
