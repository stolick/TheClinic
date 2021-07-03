import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PatientService } from '../../services/patient.service';
import { Patient } from '../../models/patient.interface';
import { map, tap } from 'rxjs/operators';

@Component({
  templateUrl: 'patient-records.page.html',
  styleUrls: ['patient-records.page.scss']
})
export class PatientRecordsPage implements OnInit {
  patientId: string;
  patient: Patient;
  constructor(private route: ActivatedRoute,
              private patientService: PatientService) {
  }

  ngOnInit() {
    this.patientId = this.route.snapshot.paramMap.get('patientId');
    this.patientService.findById(this.patientId)
      .pipe(
        tap(patient => {
          this.patient = patient
          console.log(this.patient)
        })
      ).subscribe()

  }

}
