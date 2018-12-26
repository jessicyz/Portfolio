//
//  Chart.swift
//   Saiorse
//
//  Created by Jess Zhu on 12/4/18.
//  Copyright © 2018 Jess Zhu. All rights reserved.
//
//jessicyz@usc.edu
import Foundation

class Chart{
    
    private var stitchModel : StitchesModel = StitchesModel.sharedInstance
    
    private var arr_numberStitchesUsed = [NumberStitchesUsed]()
    
    private var name: String
    
    init(){
        name = ""
    }
    
    init(name_: String){
        name = name_
    }
    
    init (name_: String, arr_numStitch: [NumberStitchesUsed]){
        name = name_
        arr_numberStitchesUsed = arr_numStitch
    }
    
    //update name of chart
    func updateName(newName: String){
        name = newName
    }
    
    //get name of chart
    func getChartName() -> String{
        return name
    }
    
    
    
    //every time a stitch is deleted from the stitch model, modify all the NumberStitcheUsed in the chart
    func updateStitchDeleted(){
        for stitch in arr_numberStitchesUsed{
            
            //an if statement just in case, but at the moment, the chart doesn't have to do anything
            if stitch.checkStitchDeletedFromStitchModel(){
                
            }
        }
    }
    
    //get final time
    func getFinalTime() -> Double{
        var toReturn : Double = 0
        for stitch in arr_numberStitchesUsed{
            toReturn += stitch.calculateTime()
        }
        return toReturn
    }
    
    //get size of stitches array
    func getSize() -> Int{
        return arr_numberStitchesUsed.count
    }
    
    func getStitchAt(at index: Int) -> NumberStitchesUsed?{
        if (index < 0 || index >= arr_numberStitchesUsed.count){
            return nil
        }
        return arr_numberStitchesUsed[index]
    }
    
    
    //append a stitch to arr_numberOfStitches
    //just add the stitch and the number of stitches. Does not create a new stitch. Only a NumStitchesUsed
    
    func append(name stitch: String, num: Int){
        //if the stitch already exists, just update the number
        if var checkStitchExists = findStitch(stitchName: stitch){
            checkStitchExists.updateStitchNum(num: num)
        }
        else{
            arr_numberStitchesUsed.append(NumberStitchesUsed(stitchName: stitch, num: num))
            
        }
    }
    
    func append(stitch: Stitch, num: Int){
       
        if var checkStitchExists = findStitch(stitchName: stitch.getStitchName()){
            checkStitchExists.updateStitchNum(num: num)
        }
        else{
            arr_numberStitchesUsed.append(NumberStitchesUsed(stitch_: stitch, num: num))
        }
    }
    
    //find a stitch exists
    func findStitch(stitchName : String ) -> NumberStitchesUsed? {
        
        for stitch in arr_numberStitchesUsed {
            if (stitch.getStitchName() == stitchName){
                return stitch
            }
        }
        
        return nil
    }
    
    //delete stitch from array
    func removeStitch(at index: Int){
        if (index >= 0 && index < arr_numberStitchesUsed.count){
            arr_numberStitchesUsed.remove(at: index)
        }
    }
}
