import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { FindPeoplePage } from './find-people';
import { UserPage } from '../user/user';
@NgModule({
  declarations: [
    FindPeoplePage,
    UserPage,
  ],
  imports: [
    IonicPageModule.forChild(FindPeoplePage),
  ],
  entryComponents: [
    FindPeoplePage,
    UserPage,

  ],
})
export class FindPeoplePageModule {}
