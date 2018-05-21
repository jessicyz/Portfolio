import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { YourPage } from './your';
import { eventPage } from '../event-detail/event-detail';

@NgModule({
  declarations: [
    YourPage,
    eventPage
  ],
  imports: [
    IonicPageModule.forChild(YourPage),
  ],
  entryComponents: [
    eventPage
  ],
})
export class YourPageModule {}
