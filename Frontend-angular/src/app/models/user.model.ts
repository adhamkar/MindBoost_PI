import {Gender} from "./gender.enum";

export class User {
  id!: number;
  userName!: string;
  email!: string;
  userType!: string;
  password!: string;
  phone!: string;
  gender!: Gender;
}
