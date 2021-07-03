import { Component, OnInit } from '@angular/core';
import { StorageService } from '../../services/storage.service';
import { DoctorsService } from '../../services/doctors.service';
import { PatientService } from '../../services/patient.service';
import { map, tap } from 'rxjs/operators';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { User } from '../../models/user.interface';
import { Patient } from '../../models/patient.interface';
import { Doctor } from '../../models/doctor.interface';

@Component({
  selector: 'profile',
  templateUrl: 'profile.page.html',
  styleUrls: ['profile.page.scss']
})
export class ProfilePage implements OnInit {
  patient: Patient = null;
  doctor: Doctor = null;
  updateUserForm: FormGroup;
  selectedFile: any;
  currentUser: User;

  languages = [
    'MK', 'AL', 'EN'
  ];

  constructor(private storageService: StorageService,
              private doctorsService: DoctorsService,
              private patientService: PatientService) {
  }

  ngOnInit() {
    this.currentUser = JSON.parse(this.storageService.getItem('user'));
    if(this.currentUser.roles[0] == 'ROLE_DOCTOR') {
      this.doctorsService.findById(this.currentUser.username)
        .pipe(
          map((doctor: Doctor) => {
            this.doctor = doctor;
            this.updateUserForm = this.formDefinition();
            this.addCheckboxes();
          })
        ).subscribe(_ => console.log(), error => console.log(error))
    } else if(this.currentUser.roles[0] == 'ROLE_PATIENT'){
      this.patientService.findByUsername(this.currentUser.username)
        .pipe(
          map((patient: Patient)=>{
            this.patient = patient;
            this.updateUserForm = this.formDefinition();
          })
        ).subscribe()
    }
  }

  formDefinition() {
    if(this.patient){
      return new FormGroup({
        patientName: new FormControl(this.patient.name ? this.patient.name : ''),
        patientEmbg: new FormControl(this.patient.embg.embgNumber) ,
        patientGender: new FormControl(this.patient.gender),
        image: new FormControl(),
      })
    }else if(this.doctor){
      return new FormGroup({
        doctorName: new FormControl(this.doctor.name ? this.doctor.name : ''),
        doctorLanguages: new FormArray([]),
        doctorGender: new FormControl(this.doctor.gender),
        image: new FormControl(),
      })
    }

  }

  private addCheckboxes() {
    this.languages.forEach((lang) => {
      this.formLanguages.push(new FormControl(this.doctor.languages.includes(lang) ? lang : null))
    });
  }

  get formLanguages() {
    return this.updateUserForm.controls.doctorLanguages as FormArray;
  }

  onFileSelected(event) {
      var reader = new FileReader()
      const file = event.target.files[0]
      reader.readAsDataURL(file)
      reader.onload = (_event) => {
        this.selectedFile = reader.result;
        this.updateUserForm.get('image').setValue(this.selectedFile)
      }
  }

  onCheckChange(event) {
    const formArray: FormArray = this.updateUserForm.get('doctorLanguages') as FormArray;

    /* Selected */
    if(event.target.checked){
      // Add a new control in the arrayForm
      formArray.push(new FormControl(event.target.value));
    }
    /* unselected */
    else{
      // find the unselected element
      let i: number = 0;

      formArray.controls.forEach((ctrl: FormControl) => {
        if(ctrl.value == event.target.value) {
          // Remove the unselected element from the arrayForm
          formArray.removeAt(i);
          return;
        }

        i++;
      });
    }
  }

  onSubmit(){

    if(this.currentUser.roles[0] == 'ROLE_DOCTOR') {
      if(this.selectedFile == null){
        this.updateUserForm.get('image').setValue(this.doctor.profilePicture)
      }
      console.log(this.updateUserForm.value)
      this.doctorsService.updateDoctor(this.doctor.id.id, this.updateUserForm.value)
        .pipe(
          tap(doctor =>{
            this.doctor = doctor;
          })
        )
        .subscribe()
    }else if(this.currentUser.roles[0] == 'ROLE_PATIENT'){
      if(this.selectedFile == null){
        this.updateUserForm.get('image').setValue(this.patient.profilePicture)
      }
       this.patientService.updatePatientProfile(this.patient.id.id, this.updateUserForm.value)
        .pipe(
          tap(patient =>{
            this.patient = patient;
          })
        )
        .subscribe()
    }
  }
}
