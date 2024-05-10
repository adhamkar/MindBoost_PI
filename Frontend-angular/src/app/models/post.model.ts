import { Patient} from './patient.model';
import { Therapist } from './therapist.model';
import { Comment } from './comment.model';

export class Post {
  id!: number;
  content!: string;
  createdDate!: Date;
  updatedDate!: Date;
  userVisibility!: boolean;
  patient!: Patient;
  therapist!: Therapist;
  comments!: Comment[];
}
