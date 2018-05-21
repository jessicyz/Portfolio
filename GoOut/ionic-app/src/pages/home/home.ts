import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { SignUpPage } from "../sign-up/sign-up";
import { LoginPage } from "../login/login";

@Component({
  selector: 'page-home',
  templateUrl: 'home.html',
})
export class HomePage {
	SignUpPage = SignUpPage;
	LoginPage = LoginPage;
  constructor(public navCtrl: NavController) {

  }

  swipeEvent(e){
  	//go to the login page if 
  	//the user swipes to the left
  	if(e.direction == 2){
  		this.navCtrl.push(LoginPage);
  	}
  	//go to the signup page if 
  	//the user swipes to the right
  	if(e.direction == 4){
  		this.navCtrl.push(SignUpPage);
  	}
  }
}
