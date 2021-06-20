import { Component, OnInit } from '@angular/core';
import { DoctorsService } from '../../services/doctors.service';
import { map, tap } from 'rxjs/operators';

@Component({
  selector: 'doctors',
  templateUrl: 'doctors.page.html',
  styleUrls: ['doctors.page.scss']
})
export class DoctorsPage implements OnInit{
  doctors: any;
  constructor(private doctorsService: DoctorsService) {
  }

  ngOnInit() {
   this.doctorsService.getAll()
     .pipe(
       tap(doctors => {
         console.log(doctors)
         this.doctors = doctors;
       })
     ).subscribe(_ => console.log(), error => console.log(error))
  }
}
