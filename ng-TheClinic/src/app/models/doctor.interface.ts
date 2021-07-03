import { Id } from './id.interface';
import { User } from './user.interface';

export interface Doctor {
  id: Id;
  name?: any;
  languages: any[];
  gender?: any;
  user: User;
  programs: any[];
  profilePicture?: any;
}
