//
//  SignUpVC.swift
//   Saiorse
//
//  Created by Jess Zhu on 11/24/18.
//  Copyright © 2018 Jess Zhu. All rights reserved.
//

import UIKit
import FirebaseAuth
import FirebaseDatabase

class SignUpVC: UIViewController, UITextFieldDelegate {
    

    
    // IBOutlet for email text field
    @IBOutlet weak var emailTF: UITextField!
    
    // IBOutlet for password text field
    @IBOutlet weak var passwordTF: UITextField!
    
    // IBOutlet for sign up button to style it
    @IBOutlet weak var signUpButton: UIButton!
    
    // IBOutlet to show sign up error message
    @IBOutlet weak var signUpErrorMessage: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        // disable sign up button by default
        enableSignUpButton(enabled: false)
        
        // declare delegates

        emailTF.delegate = self
        passwordTF.delegate = self
        
        // enable signup if fields are valid
        emailTF.addTarget(self, action: #selector(textFieldChanged), for: .editingChanged)
        passwordTF.addTarget(self, action: #selector(textFieldChanged), for: .editingChanged)
        
        // Empty string for error message
        signUpErrorMessage.text = ""
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        // immediately make Email first responder
        emailTF.becomeFirstResponder()
        
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        
        // resign first responder fo all text fields

        emailTF.resignFirstResponder()
        passwordTF.resignFirstResponder()
    }
    
    // disable automatic keyboard dismissal
    override var disablesAutomaticKeyboardDismissal: Bool {
        return false
    }
    
    @IBAction func backgroundTouched(_ sender: Any) {
        // tap background to dismiss keyboards

        self.emailTF.resignFirstResponder()
        self.passwordTF.resignFirstResponder()
    }
    
    @IBAction func cancelButtonTouched(_ sender: Any) {
        // dismiss back to main screen
        self.dismiss(animated: true, completion: nil)
    }
    
    @IBAction func signUpButtonTapped(_ sender: Any) {
        // call handle sign up function
        handleSignUp()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // enable the sign up button if none of the text fields are empty
    @objc func textFieldChanged(_ target:UITextField) {
        
        // check if form is filled (bool)
        let formFilled = emailTF.text != nil && emailTF.text != "" && passwordTF.text != nil && passwordTF.text != ""
        
        // enable sign up if form is filled
        enableSignUpButton(enabled: formFilled)
    }
    
    // move to other text fields
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        
        // Resigns the target textField and assigns the next textField in the form.
        switch textField {

        // if return email text field, go to password text field
        case emailTF:
            emailTF.resignFirstResponder()
            passwordTF.becomeFirstResponder()
            break
        // if return password text field, go call handle sign up function
        case passwordTF:
            handleSignUp()
            break
        default:
            break
        }
        return true
    }
    
    // enable or disable the sign up button
    func enableSignUpButton(enabled:Bool) {
        
        // if enabled
        if enabled {
            // if sign up button is enabled
            // set alpha too 100% and set bool isEnabled to true
            signUpButton.alpha = 1.0
            signUpButton.isEnabled = true
        } else {
            // else if sign up button is disabled
            // reduce alpha and set bool isEnabled to false
            signUpButton.alpha = 0.5
            signUpButton.isEnabled = false
        }
    }
    
    // declare handle sign up function
    @objc func handleSignUp() {
        
        
        
        print("Sign Up Tapped!")
        
        guard let email =  emailTF.text else {return}
        guard let pass = passwordTF.text else {return}
        
        //create user in Firebase
        
        
        Auth.auth().createUser(withEmail: email, password: pass) {user, error in
            // if error = nil and user != nil
            if error == nil && user != nil {
                //print success
                print("User successfully created")
                
                // resign first responder on TFs
                self.emailTF.resignFirstResponder()
                self.passwordTF.resignFirstResponder()
                
                //switch to success view
                //Helper.helper.switchToSuccesVC()
            } else {
                print("Error: \(error!.localizedDescription)")
                
                //display error message
                self.signUpErrorMessage.text = error!.localizedDescription
                
            }
        }
        
        
        
//        print("Sign Up Tapped!")
//
//        // get text from text fields
//        guard let email = emailTF.text else { return }
//        guard let pass = passwordTF.text else { return }
//
//
//
//
//        // create user in firebase
//        Auth.auth().createUser(withEmail: email, password: pass) { user, error in
//            // if user is not nil and error is nil
//            if error == nil && user != nil {
//
//
//                // print status
//                print("User created successfully")
//
//                // post to user database
//                //let newUser = Database.database().reference().child("users").child(user!.uid)
//
//                // update current user information at this location
//                //newUser.setValue([ "id" : "\(user!.uid)", "email": "\(email)"])
//
//                // resign first responder
//                self.emailTF.resignFirstResponder()
//                self.passwordTF.resignFirstResponder()
//
//
//                // switch to success view controller
//                Helper.helper.switchToSuccessViewController()
//            } else {
//                // print error
//                print("Error: \(error!.localizedDescription)")
//
//                // display error
//                self.signUpErrorMessage.text = error!.localizedDescription
//            }
//        }
        
        
        
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

