import { Id } from './id.interface';
import { Duration } from './duration.interface';
import { Department } from './department.interface';
import { Room } from './room.interface';

export interface Program {
  id: Id;
  programName: string;
  programDescription: string;
  duration: Duration;
  department: Department;
  rooms: Room[];
}
