//
//  NumberStitchesUsed.swift
//   Saiorse
//
//  Created by Jess Zhu on 12/4/18.
//  Copyright © 2018 Jess Zhu. All rights reserved.
//
//jessicyz@usc.edu
import Foundation

class NumberStitchesUsed{
    
    private var stitchModel : StitchesModel = StitchesModel.sharedInstance
    var stitch : Stitch?
    var numTimesUsed : Int
    
    init( stitchName : String, num : Int){
        stitch = stitchModel.findStitch(stitchName: stitchName)
        if num < 0 {
            numTimesUsed = 0
        }
        else{
            numTimesUsed = num
        }
    }
    
    init (stitch_ : Stitch, num : Int){
        stitch = stitch_
        if num < 0 {
            numTimesUsed = 0
        }
        else{
            numTimesUsed = num
        }
    }
    
    func updateStitchNum( num : Int){
        if num < 0 {
            numTimesUsed = 0
        }
        else{
            numTimesUsed = num
        }
    }
    
    func updateStitch(newStitch: Stitch){
        stitch = newStitch
    }
    
    func updateStitch(newStitch: String){
        stitch = stitchModel.findStitch(stitchName: newStitch)
    }
    
    func calculateTime() -> Double{
        if (stitch != nil){
            //force unwrap stitch because it should exist if it's gotten this far
            if let curStitchDateAndTime = stitch!.getFirstDateAndTime() {
                return curStitchDateAndTime.averageTime * Double(numTimesUsed)
            }
        }
        //if it doesn't exist, return 0 time
        return 0.0
    }
    
    func getTimeInMinuteSecond() -> String{
        var calculatedTime = calculateTime()
        var toReturn : String = ""
        var minute : Int = Int(calculatedTime/60)
        calculatedTime -= Double(minute)
        var second : Int = Int(calculatedTime)
        toReturn = String(format: "%02d:%02d", minute, second)
        return toReturn
    }
    
    func getStitchName() -> String{
        if let toReturn = stitch?.getStitchName(){
            return toReturn
        }
        return ""
    }
    
    //if deleted, update this stitch and return true
    func checkStitchDeletedFromStitchModel() -> Bool{
        
        if let checkStitchName = stitch?.getStitchName(){
            //if it exists, don't do anything
            if stitchModel.findStitch(stitchName: checkStitchName) != nil{
                return false
            }
            //if the stitch no longer exists, delete the stitch
            else{
                stitch = nil
            }
        }
        
        
        return true
    }
    
    //get number of stitches
    func getNumberTimesUsed() -> Int{
        
        //if stitch == nil, return 0. Can't use a stitch that doesn't exist
        if (stitch == nil){
            return 0
        }
        
        return numTimesUsed
    }
    
}
