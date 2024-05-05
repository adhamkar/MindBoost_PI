import { Component } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {CreatePostComponent} from "./create-post/create-post.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrl: './post.component.css'
})
export class PostComponent {
  constructor(private matDialog:MatDialog, private router:Router) {
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
  public posts:any= [
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
  ]


  getPost(id:any):any {

      this.router.navigate(['user/posts', id]);

  }
}
