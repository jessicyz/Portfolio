//
//  AddEditStitchOnChartViewController.swift
//   Saiorse
//
//  Created by Jess Zhu on 12/3/18.
//  Copyright © 2018 Jess Zhu. All rights reserved.
//
//jessicyz@usc.edu
import UIKit

class AddEditStitchOnChartViewController: UIViewController, UITextFieldDelegate {
    
    @IBOutlet weak var button_save: UIBarButtonItem!
    @IBOutlet weak var tf_numberOfStitches: UITextField!
    
    var chart : Chart?
    var stitch : NumberStitchesUsed?
    var newChartStitch : Bool = true
    var numberOfStitches : Int = -1
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        // textField has its current value
        // the new value is created from the range and string
        
        
        print("textfield delegate called")
        
        if let text = textField.text, let textRange = Range(range, in: text) {
            let updatedText = text.replacingCharacters(in: textRange,
                                                       with: string)
            
            
            
            print("update text")
            if textField == tf_numberOfStitches {
                
                
                enableSubmitButton(numberOfStitchesText: updatedText)
            }
            
        }
        
        return true
    }
    
    @IBAction func saveButtonPressed(_ sender: UIBarButtonItem) {
        tf_numberOfStitches.resignFirstResponder()
        
        if chart != nil{
            //if it's a new stitch, add it to the Chart's stitch list
            if newChartStitch{
                chart?.append(name: stitch?.getStitchName() ?? "Can't find stitch", num: numberOfStitches)
            }
            //if updating stitch, change number
            else{
                stitch?.updateStitchNum(num: numberOfStitches)
            }
            
        }
        navigationController?.popViewController(animated: true)
        
        dismiss(animated: true, completion: nil)
        
    }
    
    
    @IBAction func cancelButtonPressed(_ sender: UIBarButtonItem) {
        navigationController?.popViewController(animated: true)
        
        dismiss(animated: true, completion: nil)
        
    }
    
    
    
    @IBAction func tappedBackground(_ sender: UITapGestureRecognizer) {
        tf_numberOfStitches.resignFirstResponder()
        
    }
    
    
    func enableSubmitButton( numberOfStitchesText: String){
        
        //check if the thing is an int. if it is, continue. If not, clear the text field
        if let num = Int(numberOfStitchesText) , num > 0{
            numberOfStitches = num
        }
        else{
            tf_numberOfStitches.text = ""
        }
        
        print("Checking to enable")
        self.button_save.isEnabled = numberOfStitchesText.count > 0
        
        
        
        
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
