import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthService {
  baseUrl = 'http://localhost:9091/api/auth/';

  constructor(private _http: HttpClient) {
  }

  login(username: string, password: string) {
    const formData = {
      username: username,
      password: password
    };
    return this._http.post(`${this.baseUrl}login`, formData);
  }

}
