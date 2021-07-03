import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { StorageService } from './storage.service';
import { Program } from '../models/program.interface';

@Injectable()
export class ProgramsService{
  baseUrl = 'http://localhost:9090/api/program';
  headers: HttpHeaders;

  constructor(private _http: HttpClient,
              private storageService: StorageService) {
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${JSON.parse(storageService.getItem('user')).token}`
    })
  }

  getAll(){
    return this._http.get<Program[]>(`${this.baseUrl}`, {headers: this.headers})
  }

}
