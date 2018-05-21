import { Component } from '@angular/core';
import { LoginPage } from "../login/login";
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import {FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';

import { MainPage } from "../main/main";
import { LimitedMainPage } from '../limited-main/limited-main';
import {PasswordValidation} from './PasswordValidation';

import 'rxjs/add/operator/do';
/**
 * Generated class for the SignUpPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

 @IonicPage()
 @Component({
 	selector: 'page-sign-up',
 	templateUrl: 'sign-up.html'
 })
 export class SignUpPage {
 	//need to insert the proper servlet name

 	LoginPage = LoginPage;
 	signUpForm : FormGroup;
 	LimitedMainPage = LimitedMainPage;
 	backendURL : string  = "http://goout.us-west-1.elasticbeanstalk.com/";
 	constructor(public navCtrl: NavController, public navParams: NavParams, public formBuilder: FormBuilder) {

 		this.navCtrl = navCtrl;
 		this.signUpForm = formBuilder.group({
 			fullname: ['', Validators.compose([Validators.required, Validators.pattern("[a-zA-Z ]*"), Validators.maxLength(30)])],
 			username: ['', Validators.compose([Validators.required, Validators.pattern("[a-zA-Z]*"), Validators.maxLength(30)])],
 			password: ['', Validators.compose([Validators.required, Validators.minLength(8), Validators.maxLength(30)])],
 			confirmPassword: ['', Validators.compose([Validators.required, Validators.maxLength(30)])]
 		},{
 			validator: PasswordValidation.MatchPassword
 		}
 		);
 	};

 	// ionViewDidLoad() {
 	// 	console.log('ionViewDidLoad SignUpPage');
 	// }
 	validateSignUp(value: any): void{
 		let doc = document.getElementById('error');

 		if(this.signUpForm.valid){
 			//params:
 			//fullname
 			//username
 			//password
 			let nav = this.navCtrl;
 			let req = new XMLHttpRequest();
 			let urlADD = this.backendURL + "AddNewUser?username="+value.username + "&password=" + value.password + "&fullName=" +value.fullname + "&private=false";
 			let urlVAL = this.backendURL + "ValidateInfo?username="+value.username + "&password=" + value.password + "&fullName="+value.fullname;
 			let val = new XMLHttpRequest();
 			console.log(urlVAL);
 			val.open("get", urlVAL, true);
 			req.open("get", urlADD ,true);

 			val.send();
 			val.onreadystatechange=function(){
 				if(val.readyState === XMLHttpRequest.DONE && val.status === 200){
 					if(val.responseText.length > 0){
 						console.log(val.responseText);
 						doc.innerHTML = val.responseText;
 					}
 					else{
 						req.send();
 					}
 				}
 			}
 			req.onreadystatechange=function(){
 				if(req.readyState===XMLHttpRequest.DONE && req.status===200){
 					nav.push(LoginPage);
				}
 			}
 		}
 	}

 }
