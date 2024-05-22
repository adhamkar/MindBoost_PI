export interface Todo {
  id: number;
  task: string;
  completed: boolean;
  date: 'today' | 'tomorrow' | 'thisWeek';
}
