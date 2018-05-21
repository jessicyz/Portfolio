import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { FindEventsPage } from './find-events';
import { eventPage } from '../event-detail/event-detail';

@NgModule({
  declarations: [
    FindEventsPage,
    eventPage
  ],
  imports: [
    IonicPageModule.forChild(FindEventsPage),
  ],
  entryComponents: [
    FindEventsPage,
    eventPage
  ],
})
export class FindEventsPageModule {}
