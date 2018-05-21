import {
  GoogleMaps,
  GoogleMap,
  GoogleMapsEvent,
  GoogleMapOptions,
} from '@ionic-native/google-maps';
import { WebSocket2 } from '../../app/WebSocket';
import { Component, ViewChild, ElementRef } from '@angular/core';
import { ModalController, IonicPage, NavController, NavParams } from 'ionic-angular';
import { LoginPage } from '../login/login';
import { SignUpPage } from '../sign-up/sign-up';
import { addEventPage } from '../add-event/add-event';
import { FindPeoplePage } from '../find-people/find-people';
import { YourPage } from '../your/your';
import { FindEventsPage } from '../find-events/find-events';

import { UserPage } from '../user/user';

import { AlertController } from 'ionic-angular';
import { ToastController } from 'ionic-angular';


declare var google : any;

export interface Event {
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
  selector: 'page-main',
  templateUrl: 'main.html',
})
export class MainPage {

  events = [] as Event[];
  LoginPage = LoginPage;
  addEventPage = addEventPage;
  YourPage = YourPage;
  @ViewChild('map') mapRef : ElementRef;
  @ViewChild('search') searchRef : any;
  map: any;
  searchTerm : string;
  //alert: AlertMethods;
  // map: GoogleMap;
  constructor(public modalCtrl: ModalController, public navCtrl: NavController, public navParams: NavParams
    , public tctrl: ToastController) {
//this.alert = alert1;
    //this.am = navParams.get("AlertName");
    // connects to the server!
    WebSocket2.connectToServer(this.tctrl);
    this.initilize();
  }

  // ionViewDidLoad(){
  //   // console.log(this.mapRef);
  //   // this.showMap();
  //   setTimeout(this.showMap(), 10000);
  // }

  searchEvents() {
    this.searchTerm = this.searchRef.value;
    // console.log(this.searchTerm);
    let myModal = this.modalCtrl.create(FindEventsPage, {term : this.searchTerm});
    myModal.present();
  }

  searchPeople() {
    this.searchTerm = this.searchRef.value;
    // console.log(this.searchTerm);
    let myModal = this.modalCtrl.create(FindPeoplePage, {term : this.searchTerm});
    myModal.present();
  }

  onInput(e){
    console.log(e);
  }

  swipeEvent(e){
    //go to the login page if
    //the user swipes to the left
    if(e.direction == 2){
      this.navCtrl.push(LoginPage);
    }
    // //go to the signup page if
    // //the user swipes to the right
    if(e.direction == 4){
      this.navCtrl.push(SignUpPage);
    }
  }
  //will removed all the stored data from the local storage;
  logout(){
    //remove any further data members
    window.localStorage.removeItem('username');
    window.localStorage.removeItem('fullname');
    window.localStorage.removeItem('id');
    this.navCtrl.pop();

  }
  goToYourPage(){
    this.navCtrl.push(UserPage, {id: window.localStorage.getItem('id'), username: window.localStorage.getItem('username'), fullname:'You', followed : false})
  }

  showMap(){
    //location-- lat long
    const location = new google.maps.LatLng(34.022356, -118.285096);
    var infoWindow;
    var event1;
    //Map options
    const options = {
      center : location,
      zoom: 16,
      fullscreenControl: false,
      mapTypeId: 'roadmap',
      mapTypeControl: true,
      mapTypeControlOptions: {
        style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
        position: google.maps.ControlPosition.TOP_CENTER
      },
      zoomControl: true,
      zoomControlOptions: {
        position: google.maps.ControlPosition.TOP_RIGHT
      },
      streetViewControl: true,
      streetViewControlOptions: {
        position: google.maps.ControlPosition.RIGHT_TOP
      }

    }

    this.map = new google.maps.Map(this.mapRef.nativeElement, options);
    infoWindow = new google.maps.InfoWindow;
    var pos = {
      // lat: 34.021307, lng: -118.287950
      lat: 34.021307,
      lng: -118.287950
      //
      // lat: position.coords.latitude,
      // lng: position.coords.longitude
    };

    infoWindow.setPosition(pos);
    infoWindow.setContent('Location found.');
    infoWindow.open(this.map);
    this.map.setCenter(pos);

    // // Try HTML5 geolocation.
    // if (navigator.geolocation) {
    //   navigator.geolocation.getCurrentPosition(function(position) {
    //     var pos = {
    //       lat: position.coords.latitude,
    //       lng: position.coords.longitude
    //     };
    //
    //     infoWindow.setPosition(pos);
    //     infoWindow.setContent('Location found.');
    //     infoWindow.open(this.map);
    //     this.map.setCenter(pos);
    //   }, function() {
    //     //this.handleLocationError(true, infoWindow, map.getCenter());
    //   });
    // } else {
    //   // Browser doesn't support Geolocation
    //   // this.handleLocationError(false, infoWindow, map.getCenter());
    // }
    // console.log('made it here');
    // console.log(this.events.length);
    this.showMarkers();
  }


  showMarkers(){
    console.log("insie show markers");
    var markers = [];
    var eventInfos = [];
    var dispage = this;
    //making new markers and infowindows
    for (var i = 0; i < dispage.events.length; i++) {
      // console.log(this.events[i]);
      var contentString = '<div id="content">'+
        '<div id="siteNotice">'+
        '</div>'+
        '<h1 id="firstHeading" class="firstHeading">' + dispage.events[i].title + '</h1>'+
        '<div id="bodyContent">'+
        '<p>Hosted by ' + dispage.events[i].user +
        '</p>'+
        '</div>'+
        '</div>';
      // console.log(contentString);
      markers.push(new google.maps.Marker({
        position: dispage.getCoords(dispage.events[i].location),
        map: dispage.map,
        title: dispage.events[i].title
      }));
      console.log(dispage.events[i].location);
      // var marker = new google.maps.Marker({
      //   position: dispage.getCoords("RTH"),
      //   map: dispage.map
      // });

      eventInfos.push(new google.maps.InfoWindow({
        content: contentString
      }));
      // console.log(markers[i]);
      // console.log(eventInfos[i]);


    }

    console.log('size of markers ' + markers.length + ' info '  + eventInfos.length );
    console.log("show markers: pushed");
    //showing them

    for (var x = 0; x < eventInfos.length; x++){
      console.log("inside the 2nd loop");
      let eventInfo =  eventInfos[x] ;
      let marker = markers[x];
      markers[x].addListener('click', function() {
        // console.log(eventInfo);
        eventInfo.open(dispage.map, marker);
      });
    }
    console.log("show markers: finished");

  }

  initilize(){
    console.log('inside initialize');
    let sTerm = "";
    let page = this;
    var req = new XMLHttpRequest();
    req.open("get", "http://goout.us-west-1.elasticbeanstalk.com/FindEventBySearchTerm?searchTerm="+sTerm,true);
    req.send();
    req.onreadystatechange = function(){
      if(req.readyState === XMLHttpRequest.DONE && req.status === 200){
        console.log(req.responseText);
        if(req.responseText.length > 0){
          console.log(req.responseText);
          console.log("eventFound");
          var split = req.responseText.split("\n");
          var eventID = page.arraytify(split[0]);
          if(eventID.length > 0  && eventID[0] != ''){
            console.log(eventID.length);
            var eventTitle = page.arraytify(split[1]);
            var usernames = page.arraytify(split[2]);
            //console.log("userIDS" + userIDs);
            var eventLocation = page.arraytify(split[3]);
            for(var z=0; z<eventLocation.length; z++){
              eventLocation[z] = eventLocation[z].trim();
            }
            var eventMonths = page.arraytify(split[4]);
            var eventDays = page.arraytify(split[5]);
            var eventStart = page.arraytify(split[6]);
            var eventEnd = page.arraytify(split[7]);
            var descrip = page.arraytify(split[8]);
            for(var i = 0; i < eventTitle.length; i++){
              //let user = page.findUser(userIDs[i]);
              page.addEvent(eventID[i],eventTitle[i], usernames[i], eventLocation[i], eventMonths[i],eventDays[i], eventStart[i], eventEnd[i], descrip[i]);
            }
            console.log("finished the request");
            //page.showMap();
            page.showMap();
          }
          else{
            console.log('notfound');
          }
          // for(var i = 0; i < userIDs.length; i++){
          //   console.log(page.findUser(userIDs[i]));
          // }
        }
      }


    }
    // setTimeout(this.showMap(), 3000)

    console.log("init finihsed");
    console.log(this.events);
  }



  addEvent(id: string, title:string, user:string, location:string, month:string, day:string, start:string, end:string, descrip:string){

    this.events.push({id: id, title:title, user:user, location: location, month:month, day: day, startTime:start, endTime:end, description:descrip});

  }

  arraytify(value:any){

    return value.replace(/[\[\]']+/g,'').split(',')

  }

  getCoords(loc : string){
    if (loc == "SAL"){
      return {lat: 34.018682, lng: -118.289198};

    }
    else if (loc == "PKS"){
      return {lat: 34.018682, lng: -118.289198};

    }
    else if (loc == "GFS"){
      return {lat: 34.021307, lng: -118.287950};

    }
    else if (loc == "SGM"){
      return {lat: 34.021233, lng: -118.289150};

    }
    else if (loc == "VKC"){
      return {lat: 34.021151, lng: -118.283855};

    }
    else if (loc == "UV"){
      return {lat: 34.025081, lng: -118.285257};

    }
    else if (loc == "ANN"){
      return {lat: 34.020835, lng: -118.286569};

    }
    else if (loc == "THH"){
      return {lat: 34.022227, lng: -118.284552};
    }

    else if (loc == "THH"){
      return {lat: 34.022227, lng: -118.284552};
    }
    else if (loc == "TCC"){
      return {lat: 34.020153, lng: -118.286542};
    }
    else if (loc == "MUS"){
      return {lat: 34.022887, lng: -118.285011};
    }
    else if (loc == "RTH"){
      return {lat: 34.020126, lng: -118.289683};
    }
  }
}

// handleLocationError(browserHasGeolocation, infoWindow, pos) {
//   infoWindow.setPosition(pos);
//   infoWindow.setContent(browserHasGeolocation ?
//                               'Error: The Geolocation service failed.' :
//                               'Error: Your browser doesn\'t support geolocation.');
//   infoWindow.open(map);
//  }
// }
//  loadMap() {
//    let mapOptions: GoogleMapOptions = {
//      camera: {
//        target: {
//          lat: 43.0741904,
//          lng: -89.3809802
//        },
//        zoom: 18,
//        tilt: 30
//      }
//    };
//    this.map = GoogleMaps.create('map', mapOptions);
//    // Wait the MAP_READY before using any methods.
//    this.map.one(GoogleMapsEvent.MAP_READY).then(() => {
//        console.log('Map is ready!');
//        // Now you can use all methods safely.
//        this.map.addMarker({
//            title: 'Ionic',
//            icon: 'blue',
//            animation: 'DROP',
//            position: {
//              lat: 43.0741904,
//              lng: -89.3809802
//            }
//          })
//          .then(marker => {
//            marker.on(GoogleMapsEvent.MARKER_CLICK)
//              .subscribe(() => {
//                alert('clicked');
//              });
//          });
//      });
// }
