//
//  StitchesModel.swift
//   Saiorse
//
//  Created by Jess Zhu on 11/23/18.
//  Copyright © 2018 Jess Zhu. All rights reserved.
//
//jessicyz@usc.edu
import Foundation
//import FirebaseAuth
//import FirebaseDatabase

class StitchesModel{
    
    //var ref: DatabaseReference! = Database.database().reference()
    
    private var arr_stitches = [Stitch]()
    
    // Swift Singleton pattern
    static let sharedInstance = StitchesModel()
    
    private(set) var currentIndex : Int? = 0;
    
    
    

    //[StitchString , [Date] [Time] ]
    //temporary. Loading from plist into our class format
    //private var arr_loading:[ [String:String] ]
    
    init(){
        //testing
        arr_stitches.append( Stitch( stitchName: "knit", time: 1.0, date: Date() ) )
        arr_stitches.append( Stitch( stitchName: "purl", time: 1.2, date: Date() ) )
        arr_stitches[0].addNewTime(dateAndTime: DateAndTime(newTime: 0.8, newDate: Date(timeIntervalSinceNow: -1) ) )
        
//        let path = Bundle.main.path(forResource: "Stitches", ofType: "plist")
//        arr_loading = NSMutableArray(contentsOfFile: path!) as! Array
        
        
        
    }
    
    //get size of stitches array
    func getSize() -> Int{
        return arr_stitches.count
    }
    
    //get stitch at index without changing the current index
    
    func getStitchAt(at index: Int) -> Stitch?{
        if (index < 0 || index >= arr_stitches.count){
            return nil
        }
        return arr_stitches[index]
    }
    
    //get stitch at index and change the current index
    func stitch(setIndexTo index: Int) -> Stitch?{
        if (index < 0 || index >= arr_stitches.count){
            return nil
        }
        
        currentIndex = index
        return arr_stitches[index]
        
    }
    
    //append a stitch
    
    func append(name stitch: String, time: Double, date: Date){
        //check if it already exists. if it does, just add a new date and time to the stitch
        if var checkStitchExists = findStitch(stitchName: stitch){
            checkStitchExists.addNewTime(date: date, time: time)
        }
        else{
            arr_stitches.append(Stitch(stitchName: stitch, time: time, date: date))
        }
    }
    
    func append(name stitch: String, dateAndTime: DateAndTime){
        if var checkStitchExists = findStitch(stitchName: stitch){
            checkStitchExists.addNewTime(dateAndTime: dateAndTime)
        }
        else{
            arr_stitches.append(Stitch(stitchName: stitch, dateAndTime: dateAndTime))
        }
        
    }
    
    //find a stitch exists
    func findStitch(stitchName : String ) -> Stitch? {
        
        for stitch in arr_stitches {
            if (stitch.getStitchName() == stitchName){
                return stitch
            }
        }
        
        return nil
    }
    
    
    func deleteStitch(at index: Int){
        if (index >= 0 && index < arr_stitches.count){
            arr_stitches.remove(at: index)
        }
    }
    
    func deleteStitch(stitchName: String){

        //go through array to search for a stitch with that name
        for index in 0...arr_stitches.count{
            //remove the stitch if it has that name
            if arr_stitches[index].getStitchName() == stitchName{
                
                arr_stitches.remove(at: index)
                
                return
            }
        }
    }
    
    func updateStitchName(at stitchName: String, to newName: String){
        findStitch(stitchName: stitchName)?.updateStitchName(newName: newName)
    }
    
}
