import {Component, EventEmitter, Inject, OnInit, Output} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {PostService} from "../../services/post.service";
import {Router} from "@angular/router";
import {Post} from "../../models/post.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";


@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrl: './create-post.component.css'
})
export class CreatePostComponent implements OnInit{
  inputData:any;
  post:any;
  public postForm!: FormGroup;

constructor(@Inject(MAT_DIALOG_DATA) public data:any ,private ref:MatDialogRef<CreatePostComponent>,
            private postService:PostService, private router: Router,
            private formBuilder: FormBuilder,public authService:AuthService){
}

  ngOnInit(): void {
    this.inputData = this.data;
    this.postForm = this.formBuilder.group({
      content:this.formBuilder.control('', [Validators.required]),
      title:this.formBuilder.control('', [Validators.required]),
     // userVisibility:this.formBuilder.control(false) // Define userVisibility FormControl with initial value
      })
    }
    save(){
       this.post= this.postForm.value;
      this.postService.createPatientPost(this.post).subscribe(
        (data)=>{
          this.post=data;
          this.close();
        },
        (error) => {
          console.error('Error creating post:', error);
          this.close();
        }
      );
      }

    close(){
          this.ref.close();
    }
    onSubmit() {
      console.log("saaaaaave Poooost");

    }
  saved() {
    console.log("saaaaaave Poooost");
  }
}
