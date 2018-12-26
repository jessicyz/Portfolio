//
//  AddStitchViewController.swift
//   Saiorse
//
//  Created by Jess Zhu on 11/27/18.
//  Copyright © 2018 Jess Zhu. All rights reserved.
//
//jessicyz@usc.edu
import UIKit

class AddStitchViewController: UIViewController, UITextFieldDelegate {
    
    //if this is true, it's a new stitch. If it's false, it's an old stitch and you will rename all stitches in this
    var newStitch: Bool = true
    
    var nameOfUpdatingStitch : String = ""
    
    var startTime: Double = 0
    
    @IBOutlet weak var tf_stitchName: UITextField!
    
    @IBOutlet weak var tf_numberOfStitches: UITextField!
    
    @IBOutlet weak var button_save: UIBarButtonItem!
    
    @IBOutlet weak var button_timer: UIButton!
    
    
    @IBOutlet weak var lab_timer: UILabel!
    
    
    var numberOfStitches : Int = 0//20
    
    var stitchModel : StitchesModel = StitchesModel.sharedInstance
    
    var time : Double = -1
    
    var timerOn : Bool = false
    
    var timer : Timer = Timer()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        button_save.isEnabled = false
        // Do any additional setup after loading the view.
        //self.navigationItem.rightBarButtonItem = self.editButtonItem
        

        tf_stitchName.text = nameOfUpdatingStitch
//        stepper_numberOfStitches.value = Double(numberOfStitches)
        //tf_numberOfStitches.text = numberOfStitches.description
        print(tf_numberOfStitches.text!.count)
        
        startTime = Date().timeIntervalSinceReferenceDate
        
        //enableSubmitButton(nameText: tf_stitchName.text!, numberOfStitchesText: tf_numberOfStitches.text!)
    }
    
    //cancel button should dismiss the view
    @IBAction func CancelButtonPressed(_ sender: UIBarButtonItem) {
    
        navigationController?.popViewController(animated: true)
        
        dismiss(animated: true, completion: nil)
    }
    
    
    //save button should save the time and date
    @IBAction func SaveButtonPressed(_ sender: UIBarButtonItem) {
        tf_numberOfStitches.resignFirstResponder()
        tf_stitchName.resignFirstResponder()
        //unnamed stitch souldn't happen, because otherwise the save button wouldn't even be enabled
        if !newStitch {
            
            
            var checkStitchExists = stitchModel.findStitch(stitchName: tf_stitchName.text!)
            //if the stitch already exists and you aren't adding a new stitch
            //This will trigger if you are updating a stitch to add a new time
            if  checkStitchExists != nil{
                //it ISN'T the current stitch, send a warning and don't let the save go through
                if checkStitchExists?.getStitchName() != nameOfUpdatingStitch{
                    let alertController = UIAlertController(title: "Name Already In Use", message: "This name is already being used by another stitch. Please choose a different name", preferredStyle: .alert)
                    
                    
                    let okAction = UIAlertAction(title: "OK", style: .destructive, handler: { action in
                        
                    } )
                    
                    alertController.addAction(okAction)
                    
                    self.present(alertController, animated: true, completion: nil)
                    
                    return
                }
                
            }
            else{
                
                //if the stitch you put in the text field DOESN'T exist and you AREN'T adding a new stitch, it will rename all the stitches with the old name
                
                let titleString = NSLocalizedString("Rename stitch?", comment: "")
                let messageString = NSLocalizedString("This will rename the stitch. Is that okay?", comment: "")
                
                let alertController = UIAlertController(title: titleString, message: messageString, preferredStyle: .actionSheet)
                
                
                let cancelAction = UIAlertAction(title: "Cancel", style: .cancel, handler: { action in print("cancel action") } )
                
                let okAction = UIAlertAction(title: "YES", style: .destructive, handler: { action in



                    self.stitchModel.append(name: self.nameOfUpdatingStitch, time: self.time/Double(self.numberOfStitches), date: Date())
                    
                    self.stitchModel.updateStitchName(at: self.nameOfUpdatingStitch, to: self.tf_stitchName.text ?? "Unnamed Stitch")
                    
                    
                    
                    self.navigationController?.popViewController(animated: true)
                    
                    self.dismiss(animated: true, completion: nil)
                    
                    self.updateTables()
                } )
                alertController.addAction(cancelAction)
                alertController.addAction(okAction)
                
                self.present(alertController, animated: true, completion: nil)
                return
                
                
            }
            
            
            
        }// end of if it's not a new stitch

        //if it's an entirely new stitch, add it
        stitchModel.append(name: tf_stitchName.text ?? "Unnamed Stitch", time: time/Double(self.numberOfStitches), date: Date())
        
        navigationController?.popViewController(animated: true)
        
        dismiss(animated: true, completion: nil)
        
        updateTables()
        

        
        
        
        
        
        
        
        
    
    
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        
        if (textField == tf_stitchName){
            tf_stitchName.resignFirstResponder()
            tf_numberOfStitches.becomeFirstResponder()
        }
        else if (textField == tf_numberOfStitches){
            tf_numberOfStitches.resignFirstResponder()
        }
        enableSubmitButton(nameText: tf_stitchName.text!, numberOfStitchesText: tf_numberOfStitches.text!)
        return true
    }
    
    // enable the save button if none of the text fields are empty and there is a time
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        // textField has its current value
        // the new value is created from the range and string
        
        
        print("textfield delegate called")
        
        if let text = textField.text, let textRange = Range(range, in: text) {
            let updatedText = text.replacingCharacters(in: textRange,
                                                       with: string)
            
            
            
            print("update text")
            if textField == tf_stitchName {
                print("It's name")
                enableSubmitButton(nameText: updatedText, numberOfStitchesText: tf_numberOfStitches.text!)
            }
            else if textField == tf_numberOfStitches {

                
                enableSubmitButton(nameText: tf_stitchName.text!, numberOfStitchesText: updatedText)
            }
            
        }
        
        return true
    }
    
    func enableSubmitButton( nameText: String , numberOfStitchesText: String){
        
        //check if the thing is an int. if it is, continue. If not, clear the text field
        if let num = Int(numberOfStitchesText) , num > 0{
            numberOfStitches = num
        }
        else{
            tf_numberOfStitches.text = ""
        }
        
        print("Checking to enable")
        self.button_save.isEnabled = (nameText.count > 0 && numberOfStitchesText.count > 0 && time > 0)
        
        
        
        
    }
    
    
    @IBAction func tappedBackground(_ sender: UITapGestureRecognizer) {
        tf_numberOfStitches.resignFirstResponder()
        tf_stitchName.resignFirstResponder()
    }
    
    
    @IBAction func timerButtonTapped(_ sender: UIButton) {
        
        tf_numberOfStitches.resignFirstResponder()
        tf_stitchName.resignFirstResponder()
        
        //turn off timer if on
        if (timerOn){
            timer.invalidate()
            
            timerOn = false
            button_timer.setTitle("Start Timer", for: .normal)
            button_timer.backgroundColor = UIColor.green
            button_timer.setTitleColor(UIColor.black, for: .normal)
            enableSubmitButton(nameText: tf_stitchName.text!, numberOfStitchesText: tf_numberOfStitches.text!)
        }
        else{
            time = 0
            startTime = Date().timeIntervalSinceReferenceDate
            timer = Timer.scheduledTimer(timeInterval: 0.001, target: self,   selector: (#selector(self.updateTimer)), userInfo: nil, repeats: true)
            timerOn = true
            button_timer.setTitle("Stop Timer", for: .normal)
            button_timer.backgroundColor = UIColor.red
            button_timer.setTitleColor(UIColor.white, for: .normal)
            self.button_save.isEnabled = false
        }
        
        
    }
    
    @objc func updateTimer() {
        //time += 1
        time = Date().timeIntervalSinceReferenceDate - startTime
        lab_timer.text = String(format: "%.2fs", time)
        //Helper.helper.millisecondsToHourMinuteSecondMilliString(ms: time) //"\(time)"
    }
    
    
    
    func updateTables(){

        NotificationCenter.default.post(name: NSNotification.Name(rawValue: "loadStitchTable"), object: nil)
        NotificationCenter.default.post(name: NSNotification.Name(rawValue: "loadStitchTimesTable"), object: nil)
    
    }
    
    
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
