import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { UserPage } from './user';
import { eventPage } from '../event-detail/event-detail';

@NgModule({
  declarations: [
    UserPage,
    eventPage
  ],
  imports: [
    IonicPageModule.forChild(UserPage),
  ],
  entryComponents: [
    UserPage,
    eventPage

  ],
})
export class UserPageModule {}
 