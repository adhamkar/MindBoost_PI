import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {NoteService} from "../../services/notes.service";

@Component({
  selector: 'app-edit-note',
  templateUrl: './edit-note.component.html',
  styleUrl: './edit-note.component.css'
})
export class EditNoteComponent {
  public noteForm!: FormGroup;
constructor(    public noteService:NoteService,public dialogRef: MatDialogRef<EditNoteComponent>,
                @Inject(MAT_DIALOG_DATA) public data: any,
                private formBuilder: FormBuilder) {
  this.noteForm = this.formBuilder.group({
    title: [data.note.title, Validators.required],
    content: [data.note.content, Validators.required],
  });
}
  onNoClick(): void {
    this.dialogRef.close();
  }

  saveChanges(): void {
  this.noteForm.value.id = this.data.note.id;
  this.noteService.updateNote(this.noteForm.value).subscribe(
    (data) => {
      console.log(data);
      this.dialogRef.close();
    },
    (error) => {
      console.log(error);
    }
  );
  }
}
