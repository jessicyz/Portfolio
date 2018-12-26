//
//  SettingsVC.swift
//   Saiorse
//
//  Created by Jess Zhu on 11/26/18.
//  Copyright © 2018 Jess Zhu. All rights reserved.
//

import Foundation

import UIKit
import FirebaseDatabase

import FirebaseAuth

class SettingsVC: UIViewController {
    
    // create IB outlet for email label
    @IBOutlet weak var lab_email: UILabel!
    // declare users ref instance variable
    var usersRef = Database.database().reference().child("users")
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        // initialize email as empty string
        self.lab_email.text = "No email"
        
        
        print("Getting current user")
        // declare constant for current user object
        let currentUser = Auth.auth().currentUser
        print("Getting current user id")
        
        // declare constant for current user UID
        //let currentUID = currentUser?.uid as! String
        
        if let email =  currentUser?.email{
            self.lab_email.text = email
        }


        
//        print("getting snapshot")
//        // get full name from Database based on UID
//        usersRef.child(currentUID).observeSingleEvent(of: .value, with: { (snapshot) in
//            // Get user snapshot (dictionary)
//            let userSnapshot = snapshot.value as? NSDictionary
//
//            print("getting email")
//            // get email
//            //let email = userSnapshot?["email"] as? String ?? ""
////            let email = currentUser?.email
////            // set label to email
////            self.lab_email.text = email
//
//        }) { (error) in
//            print(error.localizedDescription)
//
//            // set emailto empty string
//            self.lab_email.text = "no email"//""
//        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func logOutTapped(_ sender: Any) {
        // call log out helper function
        Helper.helper.logOut()
    }
    
    /*
     // MARK: - Navigation
     
     // In a storyboard-based application, you will often want to do a little preparation before navigation
     override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
     // Get the new view controller using segue.destinationViewController.
     // Pass the selected object to the new view controller.
     }
     */
    
}
