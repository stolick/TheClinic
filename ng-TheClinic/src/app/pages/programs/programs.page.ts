import { Component, OnInit } from '@angular/core';
import { ProgramsService } from '../../services/programs.service';
import { map } from 'rxjs/operators';
import { Program } from '../../models/program.interface';

@Component({
  selector: 'departments',
  templateUrl: 'programs.page.html',
  styleUrls: ['programs.page.scss']
})
export class ProgramsPage implements OnInit{

  programs: Program[];

  constructor(private programsService: ProgramsService) {
  }

  ngOnInit(): void {
    this.programsService.getAll()
      .pipe(
        map((programs: Program[]) => {
          console.log(programs)
          this.programs = programs;
        })
      ).subscribe()
  }

   parseDurationString( durationString ){
    var stringPattern = /^PT(?:(\d+)D)?(?:(\d+)H)?(?:(\d+)M)?(?:(\d+(?:\.\d{1,3})?)S)?$/;
    var stringParts:any = stringPattern.exec( durationString );
    return this.secondsToTime(
      (
        (
          ( stringParts[1] === undefined ? 0 : stringParts[1]*1 )  /* Days */
          * 24 + ( stringParts[2] === undefined ? 0 : stringParts[2]*1 ) /* Hours */
        )
        * 60 + ( stringParts[3] === undefined ? 0 : stringParts[3]*1 ) /* Minutes */
      )
      * 60 + ( stringParts[4] === undefined ? 0 : stringParts[4]*1 ) /* Seconds */
    );
  }
   secondsToTime(secs)
  {
   let hours = Math.floor(secs / (60 * 60));

   let divisor_for_minutes = secs % (60 * 60);
   let minutes = Math.floor(divisor_for_minutes / 60);

   let divisor_for_seconds = divisor_for_minutes % 60;
   let seconds = Math.ceil(divisor_for_seconds);

   let obj = {
      "h": hours,
      "m": minutes,
      "s": seconds
    };
    let returnString;
    if(hours == 0)
      returnString = `${obj.m}mins`
    else if(minutes == 0)
      returnString = `${obj.h}h`
    else
      returnString = `${obj.h}h ${obj.m}mins`;
    return returnString;
  }

}
