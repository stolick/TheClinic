import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPage } from './pages/login/login.page';
import { DashboardPage } from './pages/dashboard/dashboard.page';
import { DoctorsPage } from './pages/doctors/doctors.page';
import { ProgramsPage } from './pages/programs/programs.page';
import { ProfilePage } from './pages/profile/profile.page';
import { PatientsPage } from './pages/patients/patients.page';
import { SlotsPage } from './pages/slots/slots.page';
import { WorkSchedulePage } from './pages/work-schedule/work-schedule.page';
import { PatientRecordsPage } from './pages/patient-records/patient-records.page';

const routes: Routes = [
  {
    path: 'login',
    component: LoginPage
  },{
    path: 'dashboard',
    component: DashboardPage
  },
  {
    path: 'doctors',
    component: DoctorsPage
  },{
    path: 'patients',
    component: PatientsPage
  },
  {
    path: 'patient-records/:patientId',
    component: PatientRecordsPage
  },
  {
    path: 'programs',
    component: ProgramsPage
  },
  {
    path: 'profile',
    component: ProfilePage
  },
  {
    path: 'slots/:programId',
    component: SlotsPage
  },
  {
    path: 'slots/doctor/:doctorId',
    component: SlotsPage
  },
  {
    path: 'work-schedule',
    component: WorkSchedulePage
  },
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
