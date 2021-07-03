import { Id } from './id.interface';
import { Embg } from './embg.interface';
import { User } from './user.interface';

export interface Patient {
  id: Id;
  name?: any;
  gender?: any;
  embg: Embg;
  user: User;
  profilePicture?: any;
}
