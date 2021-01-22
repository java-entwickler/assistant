import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NavbarComponent} from './components/navbar/navbar.component';
import {JobsComponent} from './components/jobs/jobs.component';
import {ClientsComponent} from './components/clients/clients.component';
import {StatisticsComponent} from './components/statistics/statistics.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {AddClientComponent} from './components/clients/add-client/add-client.component';
import {DialogModule} from 'primeng/dialog';
import {EditClientComponent} from './components/clients/edit-client/edit-client.component';
import {AddJobComponent} from './components/jobs/add-job/add-job.component';
import {EditJobComponent} from './components/jobs/edit-job/edit-job.component';
import {AccordionModule} from 'primeng/accordion';
import {ChartModule} from 'primeng/chart';
import {CalendarModule} from 'primeng/calendar';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    JobsComponent,
    ClientsComponent,
    StatisticsComponent,
    AddClientComponent,
    EditClientComponent,
    AddJobComponent,
    EditJobComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    DialogModule,
    AccordionModule,
    ChartModule,
    CalendarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
