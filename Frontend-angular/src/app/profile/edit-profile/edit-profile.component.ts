import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<EditProfileComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {}

  ngOnInit(): void {
        throw new Error('Method not implemented.');
    }

  onCancelClick(): void {
    this.dialogRef.close();
  }

  onSaveClick(updatedProfileData: any): void {
    this.dialogRef.close(updatedProfileData);
  }
}
