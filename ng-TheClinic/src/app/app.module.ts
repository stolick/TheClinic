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
import { DepartmentsPage } from './pages/departments/departments.page';
import { ProfilePage } from './pages/profile/profile.page';
import { DoctorsService } from './services/doctors.service';
import { PatientService } from './services/patient.service';
import { DepartmentsService } from './services/departments.service';

const components = [
 AppComponent
];

const pages = [
  LoginPage,
  DashboardPage,
  DoctorsPage,
  DepartmentsPage,
  ProfilePage
];

const modules = [
  BrowserModule,
  AppRoutingModule,
  HttpClientModule
];

const services = [
  AuthService,
  StorageService,
  DoctorsService,
  PatientService,
  DepartmentsService
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
  providers: [...services],
  bootstrap: [AppComponent]
})
export class AppModule { }
