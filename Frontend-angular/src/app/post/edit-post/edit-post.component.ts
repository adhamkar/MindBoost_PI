import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-edit-post',
  templateUrl: './edit-post.component.html',
  styleUrl: './edit-post.component.css'
})
export class EditPostComponent implements OnInit{

  editPostForm!: FormGroup;
  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<EditPostComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){}
  ngOnInit(): void {
    this.editPostForm = this.fb.group({
      content: [this.data.post.content]
    });
  }
  edit() {
    if (this.editPostForm.valid) {
      const updatedPost = this.editPostForm.value;
      // Logique pour mettre à jour le post
      this.dialogRef.close(updatedPost);
    }

  }
}
