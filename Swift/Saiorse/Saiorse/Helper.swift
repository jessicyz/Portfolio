//
//  Helper.swift
//   Saiorse
//
//  Created by Jess Zhu on 11/23/18.
//  Copyright © 2018 Jess Zhu. All rights reserved.
//

import Foundation
import UIKit
import FirebaseAuth

class Helper {
    static let helper = Helper()
    
    // switch to success view
    func switchToSuccessViewController() {
        // create main storyboard instance
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        
        // from main storyboard, instantiate success view
        let successVC = storyboard.instantiateViewController(withIdentifier: "TabBarController")
        
        // get the app delegate
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        
        // set the success view controller as root view controller
        appDelegate.window?.rootViewController = successVC
    }
    
    // log out function
    func logOut() {
        do {
            try Auth.auth().signOut()
        } catch let error {
            print(error)
        }
        
        // switch to loginViewController
        // create main storyboard instance
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        
        // from main storyboard, instantiate success view
        let logInVC = storyboard.instantiateViewController(withIdentifier: "LogInVC")
        
        // get the app delegate
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        
        // set the success view controller as root view controller
        appDelegate.window?.rootViewController = logInVC
        
    }
    
    //not a VC helper function, but whatever. I'm not making an entire class just for this
    func secondsToHourMinuteSecondString(s: Double) -> String{
        var toReturn : String = ""
        var var_time = s
        let hour : Int = Int(var_time/3600)
        var_time -= Double(hour)
        let minute : Int = Int(var_time/60)
        var_time -= Double(minute)
        let second : Int = Int(var_time)

        
        toReturn = String(format: "%02d:%02d:%02d", hour, minute, second)
        
        return toReturn
    }

    
}
