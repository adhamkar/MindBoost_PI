// src/app/notes/notes.component.ts
import {Component, Inject, OnInit} from '@angular/core';
import { NoteService } from '../services/notes.service';
import { Note } from '../models/note.model';
import { faStickyNote } from '@fortawesome/free-solid-svg-icons';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {PostService} from "../services/post.service";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit{

  notes: any[] = [
    { title: 'User Interviews to improve your product', content: 'You cannot understand good design if you do not understand people.', date: '29 Nov 2022', time: '10:30 PM, Monday' },
    { title: 'Design challenges', content: 'Design challenges are exercises or competitions that designers can do to boost creativity, create...', date: '28 Nov 2022', time: '10:30 PM, Monday' },
    { title: 'Find Inspiration for a Design Pro', content: 'Finding inspiration as a designer is more of a state of...', date: '29 Nov 2022', time: '10:30 PM, Monday' },
    { title: 'Learn how to design icons for UI', content: 'In this fast forward-moving society, where technology plays a role in...', date: '28 Nov 2022', time: '10:30 PM, Monday' },
    { title: 'Gestalt principles in UI design', content: 'How to become a master manipulator of Visual Communication and...', date: '30 Nov 2022', time: '10:30 PM, Monday' }
  ];

  editingNoteIndex: number = -1;
  editedNote: any = { title: '', content: '', date: '',time:'' };

  constructor(private noteService:NoteService, private router: Router,
              private formBuilder: FormBuilder,public authService:AuthService){
  }
  ngOnInit(): void {

    this.noteForm = this.formBuilder.group({
      content:this.formBuilder.control('', [Validators.required]),
      title:this.formBuilder.control('', [Validators.required]),
      // userVisibility:this.formBuilder.control(false) // Define userVisibility FormControl with initial value
    })
  }
  quickNotes: string[] = ['Design an Instagram story.', 'Check client feedback.'];
  newQuickNote: string = '';
  public noteForm!: FormGroup;

  addQuickNote(): void {
    if (this.newQuickNote.trim()) {
      this.quickNotes.push(this.newQuickNote);
      this.newQuickNote = '';
    }
  }
  deleteQuickNote(index: number): void {
    this.quickNotes.splice(index, 1);
  }


  editNote(index: number): void {
    this.editingNoteIndex = index;
    // Copy the note to be edited
    this.editedNote = { ...this.notes[index] };
  }

  cancelEdit(): void {
    this.noteForm = this.formBuilder.group({
      content: this.formBuilder.control(''),
      title: this.formBuilder.control(''),
    });
  }

  saveChanges(): void {
    // Update the note in the array
    this.notes[this.editingNoteIndex] = { ...this.editedNote };
    this.cancelEdit();
  }

  save() {

  }

  deleteNote(i: number) {
    
  }
}
