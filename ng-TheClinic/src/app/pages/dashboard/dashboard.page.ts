import { Component, OnInit } from '@angular/core';
import { StorageService } from '../../services/storage.service';
import { Router } from '@angular/router';

@Component({
  templateUrl: 'dashboard.page.html',
  styleUrls: ['dashboard.page.scss']
})
export class DashboardPage implements OnInit {
  currentUser: any;
  selectedFragment: string;

  constructor(private storageService: StorageService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.currentUser = JSON.parse(this.storageService.getItem('user'));
  }

  logout(){
    this.storageService.removeItem('user');
    this.router.navigate(['login']);
    window.location.reload();
  }
}
