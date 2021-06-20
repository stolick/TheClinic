import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { StorageService } from './storage.service';

@Injectable()
export class DepartmentsService{
  baseUrl = 'http://localhost:9090/api/department';
  headers: HttpHeaders;

  constructor(private _http: HttpClient,
              private storageService: StorageService) {
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${JSON.parse(storageService.getItem('user')).token}`
    })
  }

  getAll(){
    return this._http.get(`${this.baseUrl}`)
  }
}
