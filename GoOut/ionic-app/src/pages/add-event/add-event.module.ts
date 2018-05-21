import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { addEventPage } from './add-event';

@NgModule({
  declarations: [
    addEventPage,
  ],
  imports: [
    IonicPageModule.forChild(addEventPage),
  ],
})
export class AddEventPageModule {}
