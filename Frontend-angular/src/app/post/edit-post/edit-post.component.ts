import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Post} from "../../models/post.model";

@Component({
  selector: 'app-edit-post',
  templateUrl: './edit-post.component.html',
  styleUrl: './edit-post.component.css'
})
export class EditPostComponent implements OnInit{
  editPostForm!: FormGroup;
  post!: Post;
  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<EditPostComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){}
  ngOnInit(): void {
    this.post = this.data.post;
    this.editPostForm = this.fb.group({
      content: [this.post.content]
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
