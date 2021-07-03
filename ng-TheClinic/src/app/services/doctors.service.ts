import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { StorageService } from './storage.service';
import { Observable } from 'rxjs';
import { Doctor } from '../models/doctor.interface';

@Injectable()
export class DoctorsService {
  baseUrl = 'http://localhost:9090/api/doctor';
  headers: HttpHeaders;

  constructor(private _http: HttpClient,
              private storageService: StorageService) {
     this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${JSON.parse(storageService.getItem('user')).token}`
    })
  }

  getAll() {
    return this._http.get<Doctor[]>(`${this.baseUrl}`, {headers: this.headers});
  }

  findById(username: string){
    return this._http.get(`${this.baseUrl}/${username}`, {headers: this.headers});
  }

  updateDoctor(doctorId, updateDoctorForm){
    const formData = {
      name: updateDoctorForm.doctorName,
      languages: updateDoctorForm.doctorLanguages,
      gender: updateDoctorForm.doctorGender,
      profilePicture: updateDoctorForm.image
    };
    return this._http.post<Doctor>(`${this.baseUrl}/${doctorId}`, formData, {headers: this.headers});
  }
}
