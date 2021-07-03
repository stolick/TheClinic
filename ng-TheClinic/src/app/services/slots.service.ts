import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { StorageService } from './storage.service';
import { Program } from '../models/program.interface';
import { Slot } from '../models/slot.interface';
import { Patient } from '../models/patient.interface';

@Injectable()
export class SlotsService{
  baseUrl = 'http://localhost:9090/api/slot';
  headers: HttpHeaders;

  constructor(private _http: HttpClient,
              private storageService: StorageService) {
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${JSON.parse(storageService.getItem('user')).token}`
    })
  }

  getAllByProgram(programId: string){
    return this._http.get<Slot[]>(`${this.baseUrl}/program/${programId}`, {headers: this.headers})
  }

  getAllByDoctor(doctorId: string){
    return this._http.get<Slot[]>(`${this.baseUrl}/doctor/${doctorId}`, {headers: this.headers})
  }

  createSlots(formData){
    return this._http.post<Slot[]>(`${this.baseUrl}`, formData, {headers: this.headers});
  }
}
