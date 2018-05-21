import { Component} from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

// import { WebSocket } from '../../app/WebSocket';

import { WebSocket2 } from '../../app/WebSocket';


// import {Geolocation} frnom '@ionic-native/geolocation';
import { FormBuilder, FormGroup, Validators, AbstractControl } from "@angular/forms";
/**
 * Generated class for the AddEventPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */



@IonicPage()
@Component({
  selector: 'page-add-event',
  templateUrl: 'add-event.html'
})
export class addEventPage {
  public event = {
    month: '2017-11-21',
    timeStarts: '07:00',
    timeEnds: '2021-02-20'
  }


  // latitude : any;b
  // longitude : any;
  // radius: any;
  // placeSearch: any;
  // autocomplete: any;
  // circle: any;
  // geolocation : any;
  addEventForm: FormGroup;
  myDate : string = new Date().toISOString();
  constructor(public navCtrl: NavController, public navParams: NavParams, public formBuilder: FormBuilder) {
    this.navCtrl = navCtrl;
    this.addEventForm = formBuilder.group({
      title: ['', Validators.required],
      date: [''],
      time: [''],
      endTime: [''],
      location: ['', Validators.required],
      description: ['', Validators.required],
      isPrivate: ['', Validators.required]
    });
  };

  sendMessage(value: any): void{
    if(this.addEventForm.valid){
      console.log("valid");
      this.navCtrl.pop();
      console.log("in send message");
      var title = value.title;
      var date = value.date;
      var time = value.time;
      var location = value.location;
      var description = value.description;
      var isPrivate = "false";
      var endTime = value.endTime;
      var dateList = date.split("-");
      var timeList = time.split(":");



      var message = dateList[2]+"/"+dateList[1]+"/2017/"+timeList[0]+"/"+timeList[1]+"/00/1/"+title;


      //dd/MM/yyyy/hh/mm/ss/minuteDif/eventName

      //message = "21/11/2017/3/16/00/3/Startup Thing";
      console.log("date: "+date);
      console.log("start time: "+time);
      //let newDate = date.split('/');

      this.addEvent(title, dateList[1], dateList[2], time, endTime, location, description);
      console.log("message: "+message);
      WebSocket2.sendMessage(message);
      //WebSocket.send(message);
      console.log("message sent!");

    }
  }
   ionViewDidLoad() {

   }
   addEvent(title, month, day, time, endTime, location, description ){
     let req=new XMLHttpRequest();
     //console.log(date + " " + time + " " + endTime);
     let url = "http://goout.us-west-1.elasticbeanstalk.com/AddNewEvent?eventName="+title +
                 "&userID="+window.localStorage.getItem('id').replace(' ', '') + '&location='+location +
                 "&month=" + month  + "&day="+ day + "&startTime="+time +":00" + "&endTime="+endTime;
       console.log(month)
       console.log(window.localStorage.getItem('id'));
       console.log(day);
       console.log(url);
      req.open('get', url, true);
      req.onreadystatechange= function(){
        if (req.readyState === XMLHttpRequest.DONE && req.status === 200){
          console.log(req.responseText);
        }
      }
      req.send();
     //this.navCtrl.pop();
   }
   // validate(value:any):void{
   //   if(this.addEventForm.valid){
   //     console.log("valid");
   //     this.navCtrl.pop();
   //   }
   // }
}