import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { eventPage } from '../event-detail/event-detail';

export interface Event{
  id:string;
  title: string;
  user: string;
  location: string;
  month: string;
  day: string;
  startTime: string;
  endTime: string;
  description:string;
}

@IonicPage()
@Component({
  selector: 'page-user',
  templateUrl: 'user.html',
})
export class UserPage {
	eventPage = eventPage;
	username:string;
	fullname:string;
	id:string;
	events = [] as Event[];
  createdEvents = [] as Event[];
	nav: NavController;
  constructor(public navCtrl: NavController, public navParams: NavParams) {
  	this.fullname = navParams.get('fullname');
  	this.nav = navCtrl;
  	console.log(this.fullname);
  	this.id = navParams.get('id');
  	this.username = navParams.get('username');
  	this.getEvents();
    this.getCreatedEvents();
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad UserPage');
  }
  getEvents(){
  	let req = new XMLHttpRequest();
  	let page = this;
  	console.log(this.id);
  	let url = "http://goout.us-west-1.elasticbeanstalk.com/GetFollowingEventList?userID=" + this.id.replace(' ', '');
    console.log(url);
  	req.open('get', url, true);
  	req.send();

  	req.onreadystatechange = function(){
  		if(req.readyState === XMLHttpRequest.DONE && req.status === 200){
  			console.log(req.responseText);
  			let split = req.responseText.split('\n');
  			for(var i = 0; i < split.length; i++){
  				//username(creator), eventID, title, description, month, day, start, end, location
  				let event = page.arraytify(split[i]);
  				for(var j = 0; j < event.length; j++){
  					console.log(event[j]);
            if(event[j] === "null"){
              event[j] ='';
            }
  				}
  				let user = event[0];
  				let id = event[1];
  				let title = event[2];
  				
  				let descrip = event[3];
  				if(descrip === "null"){
  					descrip = '';
  				}
  				let month = event[4];
  				let day = event[5];
  				let start = event[6];
  				let end = event[7];
  				let location = event[8];
  				page.addEvent(id, title, user, location, month, day, start, end, descrip);
  			}
  		}
      
  	}
  }
  isYours(){
    if(this.fullname == 'You')
      return true;
    return false;
  }
  getCreatedEvents(){
    let req = new XMLHttpRequest();
    let page = this;
    console.log(this.id);

    //change url!!!
    let url = "http://goout.us-west-1.elasticbeanstalk.com/FindEventByUserID?userID=" + this.id.replace(' ', '');
    console.log(url);
    req.open('get', url, true);
    req.send();

    req.onreadystatechange = function(){
      if(req.readyState === XMLHttpRequest.DONE && req.status === 200){
        console.log(req.responseText);
        let split = req.responseText.split('\n');
        for(var i = 0; i < split.length; i++){
          //username(creator), eventID, title, description, month, day, start, end, location
          let event = page.arraytify(split[i]);
          for(var j = 0; j < event.length; j++);
            console.log("EVENT" + event[j]);
            if(event[j] === "null"){
              event[j] ='';
            }
          }
          let user = event[0];
          let id = event[1];
          let title = event[2];
          
          let descrip = event[3];
          if(descrip === "null"){
            descrip = '';
          }
          let month = event[4];
          let day = event[5];
          let start = event[6];
          let end = event[7];
          let location = event[8];
          page.addCreatedEvent(id, title, user, location, month, day, start, end, descrip);
        }
      }
    }
  
  viewDetails(event){
    this.nav.push(eventPage, {id: event.id, title:event.title, user:event.user, location:event.location,
      month: event.month, day:event.day, start:event.startTime, end:event.endTime,
      description:event.description});
  }
  arraytify(value:any){
    return value.replace(/[\[\]']+/g,'').split(',');
  }
  addEvent(id: string, title:string, user:string, location:string, month:string, day:string, start:string, end:string, descrip:string){
    this.events.push({id: id, title:title, user:user, location: location, month:month, day: day, startTime:start, endTime:end, description:descrip});
  }
  addCreatedEvent(id: string, title:string, user:string, location:string, month:string, day:string, start:string, end:string, descrip:string){
    this.createdEvents.push({id: id, title:title, user:user, location: location, month:month, day: day, startTime:start, endTime:end, description:descrip});
  }
  isFollowingEvent(event){
    // let req = new XMLHttpRequest();
    // let url = "http://goout.us-west-1.elasticbeanstalk.com/CheckFollowingUser?followedByUserID=" + window.localStorage.getItem('id') + "&followedEventID="+ event.id;
    // req.open('get', url, true);
    // req.send();
    // req.onreadystatechange = function(){
    //  if(req.readyState === XMLHttpRequest.DONE && req.status === 200){
    //    if(req.responseText.indexOf('yes') != -1){
    //      return true;
    //    }
    //    return false;
    //  }
    // }
    return false;
  }
  isFollowing(){
    let req = new XMLHttpRequest();
    let url = "http://goout.us-west-1.elasticbeanstalk.com/CheckFollowingUser?followedByUserID=" + window.localStorage.getItem('id') + "&followedUserID="+ this.id;
    req.open('get', url, true);
    req.send();
    req.onreadystatechange = function(){
     if(req.readyState === XMLHttpRequest.DONE && req.status === 200){
       if(req.responseText.indexOf('yes') != -1){
         return true;
       }
       return false;
     }
    }
  }

  follow(event){
    let req = new XMLHttpRequest();
    let url = "http://goout.us-west-1.elasticbeanstalk.com/FollowEvent?userID=" + window.localStorage.getItem('id') + "&eventID="+ event.id;
    req.open('get', url, true);
    req.send();
  }
  unfollow(event){
    let req = new XMLHttpRequest();
    let url = "http://goout.us-west-1.elasticbeanstalk.com/UnfollowEvent?userID=" + window.localStorage.getItem('id') + "&eventID="+ event.id;
    req.open('get', url, true);
    req.send();
  }
}

