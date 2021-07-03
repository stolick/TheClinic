import {
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  Injectable, OnInit,
  ViewEncapsulation,
} from '@angular/core';
import { CalendarEvent, CalendarEventTimesChangedEvent, CalendarEventTitleFormatter } from 'angular-calendar';
import { WeekViewHourSegment } from 'calendar-utils';
import { fromEvent, Subject } from 'rxjs';
import { finalize, map, takeUntil, tap } from 'rxjs/operators';
import { addDays, addMinutes, endOfWeek } from 'date-fns';
import { Doctor } from '../../models/doctor.interface';
import { User } from '../../models/user.interface';
import parse from 'parse-duration'
import { DoctorsService } from '../../services/doctors.service';
import { StorageService } from '../../services/storage.service';
import { ProgramsService } from '../../services/programs.service';
import { Program } from '../../models/program.interface';
import { DatePipe } from '@angular/common';
import { SlotsService } from '../../services/slots.service';
import { colors } from '../../utils/colors';
function floorToNearest(amount: number, precision: number) {
  return Math.floor(amount / precision) * precision;
}

function ceilToNearest(amount: number, precision: number) {
  return Math.ceil(amount / precision) * precision;
}

@Injectable()
export class CustomEventTitleFormatter extends CalendarEventTitleFormatter {
  weekTooltip(event: CalendarEvent, title: string) {
    if(!event.meta.tmpEvent) {
      return super.weekTooltip(event, title);
    }
  }

  dayTooltip(event: CalendarEvent, title: string) {
    if(!event.meta.tmpEvent) {
      return super.dayTooltip(event, title);
    }
  }
}

@Component({
  selector: 'work-schedule',
  changeDetection: ChangeDetectionStrategy.Default,
  templateUrl: 'work-schedule.page.html',
  providers: [
    {
      provide: CalendarEventTitleFormatter,
      useClass: CustomEventTitleFormatter,
    },
  ],
  styles: [
      `
      .disable-hover {
        pointer-events: none;
      }
    `,
  ],
  encapsulation: ViewEncapsulation.None,
})
export class WorkSchedulePage implements OnInit {
  viewDate = new Date();

  events: CalendarEvent[] = [];
  currentEvents: CalendarEvent[] = [];
  dragToCreateActive = false;

  weekStartsOn: 0 = 0;
  refresh1: Subject<any> = new Subject();
  doctor: Doctor = null;
  programs: Program[];
  currentUser: User;
  selectedProgram: Program;

  constructor(private cdr: ChangeDetectorRef,
              private doctorsService: DoctorsService,
              private storageService: StorageService,
              private programsService: ProgramsService,
              private slotService: SlotsService,
              public datepipe: DatePipe) {
  }

  ngOnInit() {
    this.currentUser = JSON.parse(this.storageService.getItem('user'));
    if(this.currentUser.roles[0] == 'ROLE_DOCTOR') {
      this.doctorsService.findById(this.currentUser.username)
        .pipe(
          map((doctor: Doctor) => {
            this.doctor = doctor;
            this.getAllPrograms()
          })
        ).subscribe(_ => console.log(), error => console.log(error))
    }
  }

  getAllPrograms() {
    this.programsService.getAll()
      .pipe(
        tap(programs => {
          this.programs = programs;
          this.selectedProgram = programs[0];
          this.getAllSlots()
        })
      ).subscribe()
  }

  getAllSlots() {
    this.slotService.getAllByDoctor(this.doctor.id.id)
      .pipe(
        tap(slots => {
          slots.forEach(slot => {
            this.currentEvents.push({
              id: slot.id.id,
              start: new Date(slot.slotDuration.slotStartTime),
              end: new Date(slot.slotDuration.slotEndTime),
              title: slot.program ? slot.program.programName : '',
              color: colors.green,
              meta: {
                tmpEvent: true
              }
            })
          })
          console.log(slots)
        })
      ).subscribe()
  }

  startDragToCreate(
    segment: WeekViewHourSegment,
    mouseDownEvent: MouseEvent,
    segmentElement: HTMLElement
  ) {
    const dragToSelectEvent: CalendarEvent = {
      id: this.events.length,
      title: this.selectedProgram.programName,
      start: segment.date,
      color: colors.green,
      draggable: true,
      meta: {
        tmpEvent: true
      },
      end: new Date(segment.date.getTime() + parse(this.selectedProgram.duration.programDuration))
    };
    this.events = [...this.events, dragToSelectEvent];
    const segmentPosition = segmentElement.getBoundingClientRect();
    this.dragToCreateActive = true;
    const endOfView = endOfWeek(this.viewDate, {
      weekStartsOn: this.weekStartsOn,
    });

    fromEvent(document, 'mousemove')
      .pipe(
        finalize(() => {
          delete dragToSelectEvent.meta.tmpEvent;
          this.dragToCreateActive = false;
          this.refresh();
        }),
        takeUntil(fromEvent(document, 'mouseup'))
      )
      .subscribe((mouseMoveEvent: MouseEvent) => {


        const minutesDiff = ceilToNearest(
          mouseMoveEvent.clientY - segmentPosition.top,
          30
        );

        const daysDiff =
          floorToNearest(
            mouseMoveEvent.clientX - segmentPosition.left,
            segmentPosition.width
          ) / segmentPosition.width;

        const newEnd = addDays(addMinutes(segment.date, minutesDiff), daysDiff);
        if(newEnd > segment.date && newEnd < endOfView) {
         // dragToSelectEvent.end = newEnd;
        }
        this.refresh();
      });
  }

  private refresh() {
    this.events = [...this.events];
    this.cdr.detectChanges();
  }

  saveEvents() {
    let eventsForm: any[] = [];
    this.events.forEach(event => {
      eventsForm.push({
        'slotStartTime': this.datepipe.transform(event.start, 'yyyy-MM-dd HH:mm'),
        'slotEndTime': this.datepipe.transform(event.end, 'yyyy-MM-dd HH:mm'),
        'slotStatus': 'AVAILABLE',
        'programId': this.programs.filter(p => p.programName == event.title)[0].id.id,
        'doctorId': this.doctor.id.id
      })
    })
    this.slotService.createSlots(eventsForm).subscribe()
    this.events = []
    window.location.reload()
  }

  eventTimesChanged({
                      event,
                      newStart,
                      newEnd,
                    }: CalendarEventTimesChangedEvent): void {
    event.start = newStart;
    event.end = newEnd;
    this.refresh1.next();
  }
}
