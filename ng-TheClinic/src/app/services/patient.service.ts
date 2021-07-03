import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { StorageService } from './storage.service';
import { Patient } from '../models/patient.interface';

@Injectable()
export class PatientService{
  baseUrl = 'http://localhost:9090/api/patient';
  headers: HttpHeaders;

  constructor(private _http: HttpClient,
              private storageService: StorageService) {
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${JSON.parse(storageService.getItem('user')).token}`
    })
  }

  getAll() {
    return this._http.get<Patient[]>(`${this.baseUrl}`, {headers: this.headers});
  }

  findById(patientId: string){
    return this._http.get<Patient>(`${this.baseUrl}/${patientId}`, {headers: this.headers});
  }

  findByUsername(username: string){
    return this._http.get(`${this.baseUrl}/user/${username}`, {headers: this.headers});
  }

  updatePatientProfile(patientId, updatePatientForm){
    const formData = {
      name: updatePatientForm.patientName,
      gender: updatePatientForm.patientGender,
      embg: updatePatientForm.patientEmbg,
      profilePicture: updatePatientForm.image
    };
    return this._http.post<Patient>(`${this.baseUrl}/${patientId}`, formData, {headers: this.headers});
  }
}
