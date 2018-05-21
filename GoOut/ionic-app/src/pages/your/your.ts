import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { eventPage } from '../event-detail/event-detail';



@IonicPage()
@Component({
  selector: 'page-your',
  templateUrl: 'your.html',
})
export class YourPage {
	eventPage = eventPage;
  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad YourPage');
  }

}
