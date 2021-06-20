import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { StorageService } from './storage.service';
import { Observable } from 'rxjs';

@Injectable()
export class DoctorsService {
  baseUrl = 'http://localhost:9091/api/doctor';
  headers: HttpHeaders;

  constructor(private _http: HttpClient,
              private storageService: StorageService) {
     this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${JSON.parse(storageService.getItem('user')).token}`
    })
  }

  getAll() {
    return this._http.get(`${this.baseUrl}`, {headers: this.headers});
  }

  findById(username: string){
    return this._http.get(`${this.baseUrl}/${username}`, {headers: this.headers});
  }

  updateDoctor(doctorId, name, gender, languages, profilePicture){
    const formData = {
      name: name,
      languages: languages,
      gender: gender,
      profilePicture: profilePicture
    };
    return this._http.post(`${this.baseUrl}/${doctorId}`, formData, {headers: this.headers});
  }
}
