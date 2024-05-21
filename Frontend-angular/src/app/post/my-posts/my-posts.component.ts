import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {Post} from "../../models/post.model";
import {PostService} from "../../services/post.service";
import {MatDialog} from "@angular/material/dialog";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {CreatePostComponent} from "../create-post/create-post.component";
import {EditPostComponent} from "../edit-post/edit-post.component";

@Component({
  selector: 'app-my-posts',
  templateUrl: './my-posts.component.html',
  styleUrl: './my-posts.component.css'
})
export class MyPostsComponent implements OnInit{
  posts: Post[] = [];
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


  editPost(i: number) {
    const post = this.posts[i];
    this.matDialog.open(EditPostComponent,{
      width:'40%',
      enterAnimationDuration:'1000ms',
      exitAnimationDuration:'1000ms',
      data:{
        title: 'Edit Post',
        post: post
      }
    })

  }

  deletePost(i: number) {

  }
}
