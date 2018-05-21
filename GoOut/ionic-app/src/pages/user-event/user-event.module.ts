import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { userEventPage } from './user-event';

@NgModule({
  declarations: [
    userEventPage,
  ],
  imports: [
    IonicPageModule.forChild(userEventPage),
  ],
})
export class userEventPageModule {}
