import { TherapistRole } from './therapist-role.enum';
import { TherapieSession } from './therapie-session.model';
import { Comment } from './comment.model';
import { Post } from './post.model';

export class Therapist {
  id!: number;
  username!: string;
  therapistRole!: TherapistRole;
  availability!: boolean;
  therapieSessions!: TherapieSession[];
  price!: number;
  localAddress!: string;
  comments!: Comment[];
  posts!: Post[];
}
