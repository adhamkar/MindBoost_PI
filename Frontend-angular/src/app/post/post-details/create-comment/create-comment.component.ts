import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {PostService} from "../../../services/post.service";

@Component({
  selector: 'app-create-comment',
  templateUrl: './create-comment.component.html',
  styleUrl: './create-comment.component.css'
})
export class CreateCommentComponent implements OnInit{
  inputData:any;
  constructor(@Inject(MAT_DIALOG_DATA) public data:any ,private ref:MatDialogRef<CreateCommentComponent>,private postService: PostService) {
  }

  ngOnInit(): void {
    this.inputData = this.data
    console.log(this.data)
  }

  close(){
    this.ref.close();
  }

  saved() {

  }
}
