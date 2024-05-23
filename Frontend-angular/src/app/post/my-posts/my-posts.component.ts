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
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-my-posts',
  templateUrl: './my-posts.component.html',
  styleUrl: './my-posts.component.css'
})
export class MyPostsComponent implements OnInit{
  posts: any;
  public PostForm!: FormGroup;
  post:any;
  onEdit: boolean = false;
  selectedPostId: number | null = null;
  public EditForm!: FormGroup;
  constructor(private matDialog:MatDialog,
              private router:Router,
              private postService: PostService,
              public authService:AuthService,
              private formBuilder: FormBuilder,) {
  }

  ngOnInit(): void {
    this.displayPatientPosts(this.authService.getUserId());
    this.PostForm = this.formBuilder.group({
      content:this.formBuilder.control('', [Validators.required]),
      title:this.formBuilder.control('', [Validators.required]),
    });
    this.EditForm = this.formBuilder.group({
      content:this.formBuilder.control('', [Validators.required]),
      title:this.formBuilder.control(''),
    });
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
  cancelEdit(): void {
    this.PostForm = this.formBuilder.group({
      content: this.formBuilder.control(''),
      title: this.formBuilder.control(''),
    });
    this.onEdit=false;
  }
// this.getMyNotes(this.authService.getUserId());
  displayPatientPosts(name:any): void {
    this.postService.getPatientPosts(name).subscribe(
      (posts: Post[]) => {
        this.posts = posts;
      },
      (error) => {
        console.error('Error fetching posts:', error);
      }
    );
  }

  OldeditPost(i: number) {
    const post = this.posts[i];
    this.matDialog.open(EditPostComponent,{
      width:'40%',
      data:{
        title: 'Edit Post',
        post: post
      }
    })
  }
  editPost(i: number) {
    this.onEdit=true;
    this.selectedPostId = i;
    console.log('Editing post with id:', this.selectedPostId);
    const post = this.findPostById(i);
    this.EditForm.setValue({
      title: post.title,
      content: post.content
    });
  }
  findPostById(id: number): any {
    return this.posts.find((note: any) => note.id === id);
  }

  /*
    updateNote(): void {
    if (this.selectedNoteId !== null) {
      console.log('Updating note with id:', this.selectedNoteId);
      this.EditForm.value.id = this.selectedNoteId;
      console.log(this.EditForm.value)
      console.log(this.EditForm.value.id);
      this.noteService.updateNote(this.EditForm.value).subscribe(
        (data) => {
          console.log(data);
          this.getMyNotes(this.authService.getUserId());
          this.EditForm.reset();
          this.onEdit=false;
        },
        (error) => {
          console.log(error);
        }
      );
    } else {
      console.error('No note selected for update');
    }
  }
   */
  UpdatePost() {
    if (this.selectedPostId !== null) {
      console.log('Updating post with id:', this.selectedPostId);
      this.EditForm.value.id = this.selectedPostId;
      console.log(this.EditForm.value)
      console.log(this.EditForm.value.id);
      this.postService.UpadatePost(this.EditForm.value).subscribe(
        (data) => {
          console.log(data);
          this.displayPatientPosts(this.authService.getUserId()) ;
          this.EditForm.reset();
          this.onEdit=false;
        },
        (error) => {
          console.log(error);
        }
      );
    } else {
      console.error('No post selected for update');
    }
  }



  deletePost(i: number) {
this.postService.deletePatientPost(i).subscribe(
  (response) => {
    console.log(response);
    this.displayPatientPosts(this.authService.getUserId());
  },
  (error) => {
    console.error('Error deleting post:', error);
  }
);
  }

  AddPost() {
    this.post= this.PostForm.value;
    this.postService.createPatientPost(this.post).subscribe(
      (data)=>{
        this.post=data;
        this.displayPatientPosts(this.authService.getUserId())
        this.PostForm.reset()
      },
      (error) => {
        console.error('Error creating post:', error);
      }
    );
  }


}

