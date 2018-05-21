import {Component} from '@angular/core';
import { SignUpPage } from "../sign-up/sign-up";
import { MainPage } from "../main/main";
import { eventPage } from "../event-detail/event-detail";
import {IonicPage, NavController, NavParams} from 'ionic-angular';
import { userEventPage } from "../user-event/user-event";
import { addEventPage } from "../add-event/add-event";
import { FormBuilder, FormGroup, Validators, AbstractControl } from "@angular/forms";
import { LimitedMainPage } from '../limited-main/limited-main';

@IonicPage()
@Component({
  selector: 'home-page',
  templateUrl: "login.html"
})

export class LoginPage{
  SignUpPage = SignUpPage;
  MainPage = MainPage;
  // eventPage = eventPage;
  // userEventPage = userEventPage;
  loginForm : FormGroup;
  // addEventPage : addEventPage;
  LimitedMainPage = LimitedMainPage;
  backendURL : string  = "http://goout.us-west-1.elasticbeanstalk.com/";
  constructor(public navCtrl: NavController, public navParams: NavParams, public formBuilder: FormBuilder){
    this.navCtrl = navCtrl;
    this.loginForm = formBuilder.group({
      username: ['', Validators.compose([Validators.required, Validators.pattern("[a-zA-Z]*"), Validators.maxLength(30)])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(8)])]
    });
  };
  validateLogin(value: any): void{
    let nav = this.navCtrl;
    let form = this.loginForm;
    let doc = document.getElementById('errorMessage');
    if(this.loginForm.valid){
      window.localStorage.setItem('username', value.username);
      window.localStorage.setItem('password', value.password);
      let req = new XMLHttpRequest();
      req.open("get", this.backendURL + "ValidateLogin?username="+value.username + "&password=" + value.password);
      req.send();
      req.onreadystatechange=function(){
        if(req.readyState===XMLHttpRequest.DONE && req.status===200){
          console.log(req.responseText);
          if(req.responseText.indexOf('Invalid') != -1 ){
            doc.innerHTML = req.responseText;
          }
          else{
            //clear the form
            form.setValue({
              username: '',
              password: ''
            });
            console.log('signed in here is the user id: '  + req.responseText);
            window.localStorage.setItem('id', req.responseText.replace(' ', ''));
            window.localStorage.setItem('username', value.username);
            doc.innerHTML = "";
            nav.push(MainPage);
          }
        }
      }
    }
  };

}
