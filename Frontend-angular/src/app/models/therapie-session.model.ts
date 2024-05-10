import { Patient } from './patient.model';
import { Therapist} from './therapist.model';

export class TherapieSession {
  id!: number;
  nameSession!: string;
  dateSession!: Date;
  patientName!: string;
  therapistName!: string;
  patient!: Patient;
  therapist!: Therapist;
}
