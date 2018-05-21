
import { Component } from '@angular/core';
import { NavController, ViewController, NavParams } from 'ionic-angular';
import { UserPage } from '../user/user';

/**
 * Generated class for the FindPeoplePage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
export interface User{
  id:string;
  username:string;
  fullname:string;
  followed: boolean;
}
@Component({

  selector: 'page-find-people',
  templateUrl: 'find-people.html',
})
export class FindPeoplePage{
  UserPage = UserPage;
	term: string;
  users = [] as User[];
  nav : NavController;
  constructor(public viewCtrl: ViewController, public params: NavParams, public navCtrl:NavController) {
  	console.log("inside construtor of find people");
  	this.term = params.get('term');
    this.nav = navCtrl;
  }

  ionViewDidLoad(){
    console.log('reloaded');
    this.search();
  }
  dismiss() {
    this.users=[];
    this.viewCtrl.dismiss();
  }
  userFollowed(user){
    return user.followed;
  }
  search(){
    let sTerm = this.term;
    let page = this;
    let req = new XMLHttpRequest();
    req.open("get", "http://goout.us-west-1.elasticbeanstalk.com/FindUserBySearchTerm?searchTerm="+sTerm,true);
    req.send();
    req.onreadystatechange = function(){
      if(req.readyState === XMLHttpRequest.DONE && req.status === 200){
        console.log(req.responseText);
        if(req.responseText.length > 0){
          console.log("eventFound");
          var split = req.responseText.split("\n");
          var userIDs = page.arraytify(split[0]);
          var usernames = page.arraytify(split[1]);
          //console.log("userIDS" + userIDs);
          var fullNames = page.arraytify(split[2]);
          // for(var i = 0; i < userIDs.length; i++){
          //   console.log(page.findUser(userIDs[i]));
          // }
          for(var i = 0; i < userIDs.length; i++){
            //let user = page.findUser(userIDs[i]);
            if(userIDs[i] != ''){
              page.addUser(userIDs[i], usernames[i], fullNames[i]);
            }
            else{
              console.log(i);
            }
           }
          page.followedUser();
        }
        else{
          console.log('notfound');
        }
      }
    }
  }
  followedUser(){
    let page = this;
    let req = new XMLHttpRequest();
    let url = "http://goout.us-west-1.elasticbeanstalk.com/GetFollowingUserList?userID="+window.localStorage.getItem('id');
    req.open('get', url, true);
    req.send();
    req.onreadystatechange = function(){
      if(req.readyState === XMLHttpRequest.DONE && req.status === 200){
        let followed = page.arraytify(req.responseText);
        console.log(req.responseText);
        console.log(followed);
        if(followed[0] != "" && followed != '\n'){
          for(var i = 0; i < page.users.length; i++){
          console.log(page.users);

          let curr = page.users[i];
          for(var j = 0; j < followed.length; j++){
            let follow = followed[j].replace(' ', '');
            follow = follow.replace('\n', '');
            if(follow != '' && curr.id.replace(' ', '') === follow){
              console.log('here');
              console.log(curr.id);
              curr.followed = true;
            }
          }
        }
        }
        
      }
    }
  }

  addUser(id:string, username:string, fullname:string){
    this.users.push({id:id, username:username, fullname:fullname, followed:false});
  }
  viewDetails(user){
    this.nav.push(UserPage,{id:user.id, username:user.username, fullname:user.fullname});
  }
  arraytify(value:any){
    return value.replace(/[\[\]']+/g,'').split(',')
  }
  follow(user){
    let req = new XMLHttpRequest();
    let url = "http://goout.us-west-1.elasticbeanstalk.com/FollowUser?followedUserID="+ user.id.replace(' ', '') + "&followedByUserID=" + window.localStorage.getItem('id');
    req.open("get", url, true);
    console.log(url);
    //user.followed = true;
    req.send();
    this.nav.pop();
  }
  unfollow(user){
    let req = new XMLHttpRequest();
    let url = "http://goout.us-west-1.elasticbeanstalk.com/UnfollowUser?followedUserID="+ user.id.replace(' ', '') + "&followedByUserID=" + window.localStorage.getItem('id');
    req.open("get", url, true);
    console.log(url);
    //user.followed = false;
    req.send();
    this.nav.pop();
  }
}
