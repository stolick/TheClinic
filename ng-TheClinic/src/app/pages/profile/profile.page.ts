import { Component, OnInit } from '@angular/core';
import { StorageService } from '../../services/storage.service';
import { DoctorsService } from '../../services/doctors.service';
import { PatientService } from '../../services/patient.service';
import { map, tap } from 'rxjs/operators';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'profile',
  templateUrl: 'profile.page.html',
  styleUrls: ['profile.page.scss']
})
export class ProfilePage implements OnInit {
  user: any;
  updateUserForm: FormGroup;
  selectedFile: any;

  constructor(private storageService: StorageService,
              private doctorsService: DoctorsService,
              private patientService: PatientService) {
  }

  ngOnInit() {
    //TODO check gender of user and check picture
    let currentUser = JSON.parse(this.storageService.getItem('user'));
    if(currentUser.roles[0] == 'ROLE_DOCTOR') {
      this.doctorsService.findById(currentUser.username)
        .pipe(
          map((doctor: any) => {
            this.user = doctor;
            this.updateUserForm = this.formDefinition();
          })
        ).subscribe(_ => console.log(), error => console.log(error))
    } else {

    }
  }

  formDefinition() {
    return new FormGroup({
      name: new FormControl(this.user.name),
      languages: new FormControl(''),
      gender: new FormControl(this.user.gender),
      image: new FormControl(''),
    })
  }

  onFileSelected(event) {

      var reader = new FileReader()
      const file = event.target.files[0]
      reader.readAsDataURL(file)
      reader.onload = (_event) => {
        this.selectedFile = reader.result;
      }

  }
}
