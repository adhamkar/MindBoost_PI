import { Post } from './post.model';
import { Comment } from './comment.model';
import { NotePad } from './note-pad.model';
import { TherapieSession } from './therapie-session.model';

export class Patient {
  id!: number;
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
