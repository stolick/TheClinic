import { Component, OnInit } from '@angular/core';
import { tap } from 'rxjs/operators';
import { Patient } from '../../models/patient.interface';
import { PatientService } from '../../services/patient.service';

@Component({
  selector: 'patients',
  templateUrl: 'patients.page.html',
  styleUrls: ['patients.page.scss']
})
export class PatientsPage implements OnInit{
  patients: Patient[];
  constructor(private patientsService: PatientService) {
  }

  ngOnInit() {
    this.patientsService.getAll()
      .pipe(
        tap(patients => {
          this.patients = patients.filter(patient => patient.name);
        })
      ).subscribe(_ => console.log(), error => console.log(error))
  }
}
