import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { LimitedMainPage } from './limited-main';
import { FindEventsPage } from '../find-events/find-events';
import { FindPeoplePage } from '../find-people/find-people';

@NgModule({
  declarations: [
    LimitedMainPage,
  ],
  imports: [
    IonicPageModule.forChild(LimitedMainPage),
  ],
})
export class LimitedMainPageModule {}
