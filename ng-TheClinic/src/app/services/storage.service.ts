import { Injectable } from '@angular/core';

@Injectable()
export class StorageService{

  setItem(key, value){
    localStorage.setItem(key, value);
  }

  getItem(key){
    return localStorage.getItem(key);
  }

  removeItem(key){
    localStorage.removeItem(key);
  }
}
