import { Component } from '@angular/core';
import { Todo } from '../model/todo-model';
import {Router} from "@angular/router";
@Component({
  selector: 'app-to-do-list',
  templateUrl: './to-do-list.component.html',
  styleUrl: './to-do-list.component.css'
})
export class ToDoListComponent {
//   todos: Todo[] = [
//     { id: 1, task: 'Database create for company', completed: false, date: 'today' },
//     { id: 2, task: 'Website templates', completed: false, date: 'today' },
//     { id: 3, task: 'Meet work team', completed: false, date: 'today' },
//     { id: 4, task: 'Work team', completed: false, date: 'tomorrow' },
//     { id: 5, task: 'Job interview', completed: false, date: 'tomorrow' },
//     { id: 6, task: 'Research content ideas', completed: false, date: 'thisWeek' },
//     { id: 7, task: 'Consult accountant', completed: false, date: 'thisWeek' },
//     { id: 8, task: 'Print business card', completed: false, date: 'thisWeek' }
//   ];
//   constructor(private router: Router) {}
//   addTask(task: string, date: 'today' | 'tomorrow' | 'thisWeek') {
//     if (task) {
//       const newTask: Todo = {
//         id: this.todos.length + 1,
//         task,
//         completed: false,
//         date
//       };
//       this.todos.push(newTask);
//     }
//   }
//
//   toggleCompletion(id: number) {
//     const todo = this.todos.find(t => t.id === id);
//     if (todo) {
//       todo.completed = !todo.completed;
//     }
//   }
//
//   getTodosByDate(date: 'today' | 'tomorrow' | 'thisWeek'): Todo[] {
//     return this.todos.filter(t => t.date === date);
//   }
//
//   navigateTo(section: string) {
//     this.router.navigate([`/${section}`]);
//   }
// }
  todos: Todo[] = [
    { id: 1, task: 'Database create for company', completed: false, date: 'today' },
    { id: 2, task: 'Website templates', completed: false, date: 'today' },
    { id: 3, task: 'Meet work team', completed: false, date: 'today' },
    { id: 4, task: 'Work team', completed: false, date: 'tomorrow' },
    { id: 5, task: 'Job interview', completed: false, date: 'tomorrow' },
    { id: 6, task: 'Research content ideas', completed: false, date: 'thisWeek' },
    { id: 7, task: 'Consult accountant', completed: false, date: 'thisWeek' },
    { id: 8, task: 'Print business card', completed: false, date: 'thisWeek' }
  ];

  showingCalendar = false;

  constructor(private router: Router) {}

  addTask(task: string, date: 'today' | 'tomorrow' | 'thisWeek') {
    if (task) {
      const newTask: Todo = {
        id: this.todos.length + 1,
        task,
        completed: false,
        date
      };
      this.todos.push(newTask);
    }
  }

  toggleCompletion(id: number) {
    const todo = this.todos.find(t => t.id === id);
    if (todo) {
      todo.completed = !todo.completed;
    }
  }

  getTodosByDate(date: 'today' | 'tomorrow' | 'thisWeek'): Todo[] {
    return this.todos.filter(t => t.date === date);
  }

  navigateTo(section: string) {
    this.router.navigate([`/${section}`]);
  }

  showCalendar() {
    this.showingCalendar = !this.showingCalendar;
  }
}
