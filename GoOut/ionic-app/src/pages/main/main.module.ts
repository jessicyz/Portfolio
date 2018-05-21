import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { MainPage } from './main';
import { FindEventsPage } from '../find-events/find-events';
import { FindPeoplePage } from '../find-people/find-people';
//import { AlertMethods } from '../../app/Alert';



@NgModule({
  declarations: [
    MainPage,
  ],
  imports: [
    IonicPageModule.forChild(MainPage),
  ],
  //providers: [AlertMethods],
  entryComponents: [
    FindEventsPage,
    FindPeoplePage
  ],
})
export class MainPageModule {}
