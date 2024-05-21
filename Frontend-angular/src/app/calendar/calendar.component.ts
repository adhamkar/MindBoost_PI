import {Component, Input} from '@angular/core';
import { Event } from '../model/Event';
import {Todo} from "../model/todo-model";
@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.css'
})
export class CalendarComponent {
//   view: string = 'day'; // Initialize the view to 'day'
//
//   events: Event[] = [
//     { id: 1, title: 'Database create for company', start: new Date(2023, 9, 17, 9, 0), end: new Date(2023, 9, 17, 10, 0), color: 'lightblue' },
//     { id: 2, title: 'Meet work team', start: new Date(2023, 9, 17, 11, 0), end: new Date(2023, 9, 17, 12, 0), color: 'lightgreen' },
//     { id: 3, title: 'Database create for company', start: new Date(2023, 9, 17, 15, 0), end: new Date(2023, 9, 17, 16, 0), color: 'lightcoral' }
//   ];
//
//   hours: string[] = [
//     '9:00 AM', '10:00 AM', '11:00 AM', '12:00 PM',
//     '1:00 PM', '2:00 PM', '3:00 PM', '4:00 PM', '5:00 PM'
//   ];
//
//   getEventsForHour(hour: string): Event[] {
//     const [time, period] = hour.split(' ');
//     const [hourStr, minuteStr] = time.split(':');
//     let hourNum = parseInt(hourStr, 10);
//     if (period === 'PM' && hourNum !== 12) {
//       hourNum += 12;
//     }
//     const hourDate = new Date(2023, 9, 17, hourNum, parseInt(minuteStr, 10));
//     return this.events.filter(event =>
//       event.start <= hourDate && event.end > hourDate
//     );
//   }
//
//   addEventDialog() {
//     const title = prompt('Event title:');
//     const startTime = prompt('Start time (e.g., 2023-10-17T09:00:00):');
//     const endTime = prompt('End time (e.g., 2023-10-17T10:00:00):');
//     const color = prompt('Event color (e.g., lightblue):');
//     if (title && startTime && endTime && color) {
//       this.addEvent(title, new Date(startTime), new Date(endTime), color);
//     }
//   }
//
//   addEvent(title: string, start: Date, end: Date, color: string) {
//     const newEvent: Event = {
//       id: this.events.length + 1,
//       title,
//       start,
//       end,
//       color
//     };
//     this.events.push(newEvent);
//   }
// }
  @Input() todos: Todo[] = [];

  events: Todo[] = [];

  ngOnChanges() {
    this.events = this.todos.filter(todo => todo.date === 'today'); // Example filter for today
  }

  getEventClass(event: Todo): string {
    if (event.date === 'today') {
      return 'event-work';
    } else if (event.date === 'tomorrow') {
      return 'event-personal';
    } else {
      return 'event-study';
    }
  }
}
