import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {CreatePostComponent} from "./create-post/create-post.component";
import {Router} from "@angular/router";
import {PostService} from "../services/post.service";
import {Post} from "../models/post.model";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrl: './post.component.css'
})
export class PostComponent implements OnInit{
  posts: Post[] = [];
  @Output() postCreated = new EventEmitter<any>();
  constructor(private matDialog:MatDialog, private router:Router,private postService: PostService,public authService:AuthService) {
  }

  ngOnInit(): void {
      this.getAllPosts();
  }

  getAllPosts(): void {
    this.postService.getAllPosts().subscribe(
      (posts: Post[]) => {
        this.posts = posts;
      },
      (error) => {
        console.error('Error fetching posts:', error);
      }
    );
  }

  createPost(){
    this.matDialog.open(CreatePostComponent,{
      width:'40%',
      enterAnimationDuration:'1000ms',
      exitAnimationDuration:'1000ms',
      data:{
        title: 'Create Post'
      }
    });
    this.matDialog.afterAllClosed.subscribe(
       (data)=>{
          this.getAllPosts();
        }
      )
  }
  DeletePost(id:any):void {
    this.postService.deletePost(id).subscribe(
      (response) => {
        console.log('Post deleted:', response);
        this.getAllPosts();
      },
      (error) => {
        console.error('Error deleting post:', error);
      }
    );
  }

  getPost(id:any):any {

      this.router.navigate(['user/posts', id]);

  }

}
