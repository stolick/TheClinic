import { ChangeDetectionStrategy, Component, OnInit, TemplateRef, ViewChild, ViewEncapsulation } from '@angular/core';
import { CalendarEvent, CalendarEventAction, CalendarView } from 'angular-calendar';
import { colors } from '../../utils/colors';
import { SlotsService } from '../../services/slots.service';
import { ActivatedRoute } from '@angular/router';
import { tap } from 'rxjs/operators';
import { Slot } from '../../models/slot.interface';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  templateUrl: 'slots.page.html',
  styleUrls: ['slots.page.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  encapsulation: ViewEncapsulation.None,
  styles: [
      `
      .cal-week-view .cal-time-events .cal-day-column {
        margin-right: 10px;
      }

      .cal-week-view .cal-hour {
        width: calc(100% + 10px);
      }
    `,
  ],
})
export class SlotsPage implements OnInit {
  programId: string;
  doctorId: string;
  slots: Slot[];
  view: CalendarView = CalendarView.Week;
  viewDate: Date = new Date();
  events: CalendarEvent[] = [];

  @ViewChild('modalContent', { static: true }) modalContent: TemplateRef<any>;
  modalData: {
    action: string;
    event: CalendarEvent;
  };
  constructor(private service: SlotsService,
              private route: ActivatedRoute,
              private modal: NgbModal) {
  }

  ngOnInit() {
    this.programId = this.route.snapshot.paramMap.get('programId');
    this.doctorId = this.route.snapshot.paramMap.get('doctorId');
    if(this.programId) {
      this.service.getAllByProgram(this.programId)
        .pipe(
          tap(slots => {
            this.slots = slots.filter(slot => slot.slotStatus == 'AVAILABLE')
            this.getEvents(this.slots)
          })
        ).subscribe()
    } else if(this.doctorId) {
      this.service.getAllByDoctor(this.doctorId)
        .pipe(
          tap(slots => {
            this.slots = slots.filter(slot => slot.slotStatus == 'AVAILABLE')
            this.getEvents(this.slots)
          })
        ).subscribe()
    }
  }

  getEvents(slots: Slot[]) {
    slots.forEach(slot => {
      this.events.push({
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
  }

  actions: CalendarEventAction[] = [
    {
      label: '<i class="fas fa-fw fa-pencil-alt"></i>',
      a11yLabel: 'Edit',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.handleEvent('Edited', event);
      },
    },
    {
      label: '<i class="fas fa-fw fa-trash-alt"></i>',
      a11yLabel: 'Delete',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.events = this.events.filter((iEvent) => iEvent !== event);
        this.handleEvent('Deleted', event);
      },
    },
  ];
  handleEvent(action: string, event: CalendarEvent): void {
    this.modalData = { event, action };
    this.modal.open(this.modalContent, { size: 'lg' });
  }
  deleteEvent(eventToDelete: CalendarEvent) {
    this.events = this.events.filter((event) => event !== eventToDelete);
  }
  eventClicked({ event }: { event: CalendarEvent }): void {
    console.log( event);
  }
}
