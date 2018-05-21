/**
 * Created by siyuanxu on 11/20/17.
 */
import { AlertController } from 'ionic-angular';
//import { Injectable } from '@angular/core';

//@Injectable();
export class AlertMethods {
  constructor(public alertCtrl: AlertController){

  }
  public doAlert(message : string){
    let alert = this.alertCtrl.create({
      title: 'Event Notification!',
      subTitle: message,
      buttons: ['Ok']
    });

    alert.present();
  }
}
