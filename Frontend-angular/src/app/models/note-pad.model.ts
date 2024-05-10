import {Patient} from "./patient.model";

export class NotePad {
  id!: number;
  title!: string;
  content!: string;
  createdDate!: Date;
  modifiedDate!: Date;
  patientDTO!: Patient;
}
