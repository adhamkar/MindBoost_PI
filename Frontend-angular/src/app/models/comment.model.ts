import { Patient } from './patient.model';
import { Post } from './post.model';
import { Therapist } from './therapist.model';

// Définition du modèle Comment
export class Comment {
  id!: number;
  comment!: string;
  createdDate!: Date;
  patient!: Patient;
  post!: Post;
  therapist!: Therapist;
}
