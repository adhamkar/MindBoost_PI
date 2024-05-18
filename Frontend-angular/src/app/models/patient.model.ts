import { Post } from './post.model';
import { Comment } from './comment.model';
import { NotePad } from './note-pad.model';
import { TherapieSession } from './therapie-session.model';
import {User} from "./user.model";

export class Patient extends User{
  //id!: number;
  username!: string;
  age!: number;
  city!: string;
  profession!: string;
  medicalHistory!: string;
  posts!: Post[];
  comments!: Comment[];
  notePads!: NotePad[];
  therapieSessions!: TherapieSession[];
}
