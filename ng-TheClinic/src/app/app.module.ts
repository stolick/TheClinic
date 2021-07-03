import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPage } from './pages/login/login.page';
import { ReactiveFormsModule } from '@angular/forms';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AuthService } from './services/auth.service';
import { HttpClientModule } from '@angular/common/http';
import { StorageService } from './services/storage.service';
import { DashboardPage } from './pages/dashboard/dashboard.page';
import { DoctorsPage } from './pages/doctors/doctors.page';
import { ProgramsPage } from './pages/programs/programs.page';
import { ProfilePage } from './pages/profile/profile.page';
import { DoctorsService } from './services/doctors.service';
import { PatientService } from './services/patient.service';
import { ProgramsService } from './services/programs.service';
import { PatientsPage } from './pages/patients/patients.page';
import { SlotsPage } from './pages/slots/slots.page';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { CalendarHeaderComponent } from './utils/calendar-header.component';
import { SlotsService } from './services/slots.service';
import { WorkSchedulePage } from './pages/work-schedule/work-schedule.page';
import { DatePipe } from '@angular/common';
import { PatientRecordsPage } from './pages/patient-records/patient-records.page';
const components = [
 AppComponent,
  CalendarHeaderComponent
];

const pages = [
  LoginPage,
  DashboardPage,
  DoctorsPage,
  ProgramsPage,
  ProfilePage,
  PatientsPage,
  SlotsPage,
  WorkSchedulePage,
  PatientRecordsPage
];

const modules = [
  BrowserModule,
  AppRoutingModule,
  HttpClientModule,
  CalendarModule.forRoot({
    provide: DateAdapter,
    useFactory: adapterFactory,
  })
];

const services = [
  AuthService,
  StorageService,
  DoctorsService,
  PatientService,
  ProgramsService,
  SlotsService
];

@NgModule({
  declarations: [
    ...components, ...pages
  ],
  imports: [
    ...modules,
    ReactiveFormsModule,
    MDBBootstrapModule.forRoot()
  ],
  providers: [...services, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
