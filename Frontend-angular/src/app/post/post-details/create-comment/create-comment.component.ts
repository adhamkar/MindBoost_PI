import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {PostService} from "../../../services/post.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CommentService} from "../../../services/comment.service";

@Component({
  selector: 'app-create-comment',
  templateUrl: './create-comment.component.html',
  styleUrl: './create-comment.component.css'
})
export class CreateCommentComponent implements OnInit{
  inputData:any;
  selectedPostId!:string;
  commentForm!: FormGroup;
  comment:any;

  constructor(private formBuilder: FormBuilder,@Inject(MAT_DIALOG_DATA) public data:any ,private ref:MatDialogRef<CreateCommentComponent>,
              private postService: PostService,private commentService: CommentService) {
  }

  ngOnInit(): void {
    this.inputData = this.data
    //console.log(this.data)
    this.commentForm = this.formBuilder.group({
      comment: ['', Validators.required]
    });
  }

  close(){
    this.ref.close();
  }

  saved() {
    if (this.commentForm.valid) {
      this.comment = {...this.commentForm.value};
      const postId = this.data.postId;
      this.commentService.saveComment( postId,this.comment ).subscribe(
        data => {
          console.log(data);
          this.comment = data
          this.close();

        },
        error => {
          console.log(error);
          // Handle error
        }
      );
    }

  }
}
