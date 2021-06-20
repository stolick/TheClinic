import { Component, OnInit } from '@angular/core';
import { DepartmentsService } from '../../services/departments.service';
import { map } from 'rxjs/operators';

@Component({
  selector: 'departments',
  templateUrl: 'departments.page.html',
  styleUrls: ['departments.page.scss']
})
export class DepartmentsPage implements OnInit{

  departments: any[];

  constructor(private departmentService: DepartmentsService) {
  }

  ngOnInit(): void {
    this.departmentService.getAll()
      .pipe(
        map((departments: any[]) => {
          this.departments = departments;
        })
      ).subscribe()
  }

}
