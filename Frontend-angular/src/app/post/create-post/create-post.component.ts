import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {PostService} from "../../services/post.service";
import {Router} from "@angular/router";
import {Post} from "../../models/post.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

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
            private formBuilder: FormBuilder) {
}

  ngOnInit(): void {
    this.inputData = this.data;
    this.postForm = this.formBuilder.group({
      content:this.formBuilder.control('', [Validators.required]),
     // userVisibility:this.formBuilder.control(false) // Define userVisibility FormControl with initial value
      })
    }
    save(){
      const patientID = 1;
       this.post= this.postForm.value;
      this.postService.createPatientPost(this.post, patientID).subscribe(
        (data)=>{
          this.post=data;
        },
        (error) => {
          console.error('Error creating post:', error);
          this.close();
          // Handle error response
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
