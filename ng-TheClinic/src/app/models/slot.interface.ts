import { Id } from './id.interface';
import { SlotDuration } from './slot-duration.interface';
import { Doctor } from './doctor.interface';
import { Program } from './program.interface';

export interface Slot {
  id: Id;
  slotDuration: SlotDuration;
  slotStatus: string;
  program: Program;
  doctor: Doctor;
}
