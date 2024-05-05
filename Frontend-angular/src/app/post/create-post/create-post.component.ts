import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrl: './create-post.component.css'
})
export class CreatePostComponent implements OnInit{
  inputData:any;
constructor(@Inject(MAT_DIALOG_DATA) public data:any ,private ref:MatDialogRef<CreatePostComponent>) {
}

  ngOnInit(): void {
      this.inputData = this.data
    }

close(){
  this.ref.close();
}
}
