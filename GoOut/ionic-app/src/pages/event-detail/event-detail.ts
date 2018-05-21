import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

/**
 * Generated class for the EventDetailPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-event-detail',
  templateUrl: 'event-detail.html',
})
export class eventPage {
  username:string;
	title:string;
	month:string;
	day:string;
	location:string;
  start:string;
  end:string;
  description:string;
  nav : NavController;
	//description:string;
  constructor(public navCtrl: NavController, public navParams: NavParams) {
    console.log(navParams.get('title'));
    this.nav = navCtrl;
    this.username= navParams.get('user');
  	this.title = navParams.get('title');
  	this.month = navParams.get('month');
  	this.day = navParams.get('day');
    this.start = navParams.get('start');
    this.end = navParams.get('end');
  	this.location = navParams.get('location');
    this.description = navParams.get('description');
    console.log(this.description);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad EventPage');
  }
  follow(event){
    let req = new XMLHttpRequest();
    console.log(window.localStorage.getItem('id'));
    let url =  "http://goout.us-west-1.elasticbeanstalk.com/FollowEvent?userID=" + window.localStorage.getItem('id') + "&eventID=" + event.id;
     console.log(url);
     console.log(window.localStorage.getItem('id'));
    req.open('get', url, true);
    req.send();
    this.nav.pop();
  }
  isFollowed(event){
    return false;
  }
}
