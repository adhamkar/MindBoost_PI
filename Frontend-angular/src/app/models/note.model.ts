import {Patient} from "./patient.model";

export interface Note {
  id: number;
  title: string;
  content: string;
  createdDate: Date;
  modifiedDate: Date;
  patientDTO: Patient;
  date: string;
  category: string;

}
