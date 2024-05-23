import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
@Injectable({
  providedIn: 'root'
})
export class NoteService {
  private apiUrl = 'http://localhost:8085';

  constructor(private http: HttpClient) { }

  getNotes(name:any): Observable<Note> {
    return this.http.get<Note>(`${this.apiUrl}`+"/notes/patient/"+name);
  }

  addNote(note: Note): Observable<Note> {
    return this.http.post<Note>(`${this.apiUrl}`+"/patient_note", note);
  }

  updateNote(note: Note): Observable<Note> {
    return this.http.patch<Note>(`${this.apiUrl}/notes/${note.id}`, note);
  }

  deleteNote(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/notes/${id}`);
  }
}

// note.model.ts
export interface Note {
  id: number;
  title: string;
  content: string;
  date: string;
  category: string;
}
