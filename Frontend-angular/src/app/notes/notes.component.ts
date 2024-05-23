// src/app/notes/notes.component.ts
import {Component, Inject, OnInit} from '@angular/core';
import { NoteService } from '../services/notes.service';
import { Note } from '../models/note.model';
import { faStickyNote } from '@fortawesome/free-solid-svg-icons';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {PostService} from "../services/post.service";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";
import {EditNoteComponent} from "./edit-note/edit-note.component";

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit{


  myNotes : any
  editingNoteIndex: number = -1;
  editedNote: any = { title: '', content: '', date: '',time:'' };
  onEdit: boolean = false;
  selectedNoteId: number | null = null;
  public EditForm!: FormGroup;
  constructor(private noteService:NoteService, private router: Router,
              private formBuilder: FormBuilder,public authService:AuthService,public dialog: MatDialog){
  }
  ngOnInit(): void {
    this.getMyNotes(this.authService.getUserId());
    this.noteForm = this.formBuilder.group({
      content:this.formBuilder.control('', [Validators.required]),
      title:this.formBuilder.control('', [Validators.required]),
      // userVisibility:this.formBuilder.control(false) // Define userVisibility FormControl with initial value
    })
    this.EditForm = this.formBuilder.group({
      content:this.formBuilder.control('', [Validators.required]),
      title:this.formBuilder.control(''),
    });
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



  EditNote(note: Note): void {
    const dialogRef = this.dialog.open(EditNoteComponent, {
      width: '250px',
      data: { note: note }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        // Update the note with the result
        note.title = result.title;
        note.content = result.content;
        // Call your service method to update the note on the server
        this.noteService.updateNote(note).subscribe(
          (data) => {
            // Handle successful update
          },
          (error) => {
            console.error('Error updating note:', error);
          }
        );
      }
    });
  }

  cancelEdit(): void {
    this.noteForm = this.formBuilder.group({
      content: this.formBuilder.control(''),
      title: this.formBuilder.control(''),
    });
    this.onEdit=false;
  }
  getMyNotes(name:any) {
  this.noteService.getNotes(name).subscribe(
  (data)=>{
    this.myNotes = data;
  },
  (error)=> {
    console.error('Error fetching notes:', error);
  }
)
  }

/*  saveChanges(): void {
    // Update the note in the array
    this.notes[this.editingNoteIndex] = { ...this.editedNote };
    this.cancelEdit();
  }*/
  editNote(noteId: number): void {
    this.onEdit=true;
    this.selectedNoteId = noteId;
    console.log('Editing note with id:', this.selectedNoteId);
    const note = this.findNoteById(noteId);
    this.EditForm.setValue({
      title: note.title,
      content: note.content
    });
  }

  updateNote(): void {
    if (this.selectedNoteId !== null) {
      console.log('Updating note with id:', this.selectedNoteId);
      this.EditForm.value.id = this.selectedNoteId;
      console.log(this.EditForm.value)
      console.log(this.EditForm.value.id);
      this.noteService.updateNote(this.EditForm.value).subscribe(
        (data) => {
          console.log(data);
          this.getMyNotes(this.authService.getUserId());
          this.EditForm.reset();
          this.onEdit=false;
        },
        (error) => {
          console.log(error);
        }
      );
    } else {
      console.error('No note selected for update');
    }
  }


findNoteById(id: number): any {
  return this.myNotes.find((note: any) => note.id === id);
}
  SaveNotes() {
    this.myNotes= this.noteForm.value;
    this.noteService.addNote(this.myNotes).subscribe(
      (data)=>{
        this.myNotes=data;
        this.getMyNotes(this.authService.getUserId());
        this.noteForm.reset();
      },
      (error) => {
        console.error('Error creating note:', error);
      }
    );
  }

  deleteNote(i: number) {
    this.noteService.deleteNote(i).subscribe(
      (data)=>{
        this.getMyNotes(this.authService.getUserId());
      },
      (error) => {
        console.error('Error deleting note:', error);
      }
    )
  }
}
