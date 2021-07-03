import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { StorageService } from './services/storage.service';
import { Router } from '@angular/router';
import { User } from './models/user.interface';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{

  currentUser: User = null;
  selectedFragment: string;
  greeting: string = null;

  constructor(private storageService: StorageService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.currentUser = JSON.parse(this.storageService.getItem('user'));
    let today = new Date()
    let curHr = today.getHours()
    if (curHr < 12) {
      this.greeting = "Good Morning, "
    } else if (curHr < 18) {
      this.greeting = "Good Afternoon, "
    } else {
      this.greeting = "Good Evening, "
    }
  }

  logout(){
    this.storageService.removeItem('user');
    this.router.navigate(['login']).then(_ => window.location.reload());
  }

  goToDashboard(){
    this.router.navigate(['dashboard']).then(_ => window.location.reload());
  }
}
