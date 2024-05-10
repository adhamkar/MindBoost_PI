import {Component, OnInit} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {CreatePostComponent} from "./create-post/create-post.component";
import {Router} from "@angular/router";
import {PostService} from "../services/post.service";
import {Post} from "../models/post.model";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrl: './post.component.css'
})
export class PostComponent implements OnInit{
  posts: Post[] = [];
  /*public posts:any= [
    {
      id: 1,
      date: '2024-12-12',
      content:  'Lorem Ipsum is simply dummy text of the printing and typesetting industry.' +
        'Lorem Ipsum is simply dummy text of the printing and typesetting industry' +
        'Lorem Ipsum is simply dummy text of the printing and typesetting industry..'
    },
    {
      id: 2,
      date: '2024-12-12',
      content: 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.' +
        'Lorem Ipsum is simply dummy text of the printing and typesetting industry' +
        'Lorem Ipsum is simply dummy text of the printing and typesetting industry..'
    },
    {
      id: 3,
      date: '2024-12-12',
      content:  'Lorem Ipsum is simply dummy text of the printing and typesetting industry.' +
        'Lorem Ipsum is simply dummy text of the printing and typesetting industry' +
        'Lorem Ipsum is simply dummy text of the printing and typesetting industry..'
    },
    {
      id: 4,
      date: '2024-12-12',
      content:  'Lorem Ipsum is simply dummy text of the printing and typesetting industry.' +
        'Lorem Ipsum is simply dummy text of the printing and typesetting industry' +
        'Lorem Ipsum is simply dummy text of the printing and typesetting industry..'
    },
  ]*/
  constructor(private matDialog:MatDialog, private router:Router,private postService: PostService) {
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
    })
  }

  getPost(id:any):any {

      this.router.navigate(['user/posts', id]);

  }

}
