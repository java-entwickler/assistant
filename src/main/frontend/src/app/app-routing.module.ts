import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {JobsComponent} from "./components/jobs/jobs.component";
import {ClientsComponent} from "./components/clients/clients.component";
import {StatisticsComponent} from "./components/statistics/statistics.component";


const routes: Routes = [
  {path: "", redirectTo: "jobs", pathMatch: "full"},
  {path: "jobs", component: JobsComponent},
  {path: "clients", component: ClientsComponent},
  {path: "statistics", component: StatisticsComponent},
  {path: 'statistics/:id', component: StatisticsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
