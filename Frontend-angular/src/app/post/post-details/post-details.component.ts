import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ActivatedRouteSnapshot, Router} from "@angular/router";
import {Post} from "../../models/post.model";
import {User} from "../../models/user.model";
import {Comment} from "../../models/comment.model";
import {Subscription} from "rxjs";
import {PostService} from "../../services/post.service";
import {CommentService} from "../../services/comment.service";
import {CreatePostComponent} from "../create-post/create-post.component";
import {MatDialog} from "@angular/material/dialog";
import {CreateCommentComponent} from "./create-comment/create-comment.component";

@Component({
  selector: 'app-post-details',
  templateUrl: './post-details.component.html',
  styleUrl: './post-details.component.css'
})
export class PostDetailsComponent implements OnInit{
  id!:string;
  post!:Post;
  comments:Comment[]= [];
  user ?: User;
  userRole ="";
  userSub$ ?:Subscription;
  constructor( private route: ActivatedRoute,
               private router: Router,
               private postService: PostService,
               private commentService:CommentService,
               private matDialog:MatDialog,) {
  }


  ngOnInit(): void {
    this.post = new Post();

    this.id = this.route.snapshot.params['id'];

    this.postService.getPost(this.id).subscribe(data => {
      console.log(data)
      this.post = data;
    }, error => console.log(error));

    this.getAllComments();
  }

  getAllComments() {
    this.postService.getComments(this.id).subscribe(
      (data: any) => {
        this.comments = data as Comment[]; // Type assertion
      },
      (error) => {
        console.error(error);
      }
    );
  }

  createComment(postId: number) {
    this.matDialog.open(CreateCommentComponent,{
      width:'40%',
      enterAnimationDuration:'1000ms',
      exitAnimationDuration:'1000ms',
      data:{
        title: 'Create Post',
        postId: postId
      }
    });
    this.matDialog.afterAllClosed.subscribe(
      data=>{
        this.getAllComments()
      }
    )
  }

  deleteComment(id: number) {
    this.commentService.deleteComment(id).subscribe(
      data => {
        console.log(data);
        this.getAllComments();
      },
      error => console.log(error));
  }




  gotoList(){
    this.router.navigate(['user/posts']);
  }



}
