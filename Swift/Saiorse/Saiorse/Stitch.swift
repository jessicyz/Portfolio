//
//  Stitch.swift
//   Saiorse
//
//  Created by Jess Zhu on 11/23/18.
//  Copyright © 2018 Jess Zhu. All rights reserved.
//
//jessicyz@usc.edu
import Foundation

class Stitch{
    var name: String
    
    //the date and the time each stitch took
    var arr_DateAndTimes: [DateAndTime]
    
    init(stitchName: String){
        name = stitchName
        arr_DateAndTimes = [DateAndTime(newTime: 0, newDate: Date())]
    }
    
    init(stitchName: String, dateAndTime: DateAndTime){
        name = stitchName
        arr_DateAndTimes = [dateAndTime]
    }
    
    init(stitchName: String, time: Double, date: Date){
        name = stitchName
        arr_DateAndTimes = [DateAndTime(newTime: time, newDate: date)]
    }
    
    
    //adds a new time to the beginning of the array
    //all we need is to insert it in the beginning
    func addNewTime( dateAndTime: DateAndTime ){
        arr_DateAndTimes.insert(dateAndTime, at: 0)
    }
    
    func addNewTime( date : Date , time : Double ){
        arr_DateAndTimes.insert( DateAndTime(newTime: time, newDate: date)  , at: 0)
    }
    
    //delete a time
    func deleteTime(at index: Int) -> Bool{
        if (index < 0 || index >= arr_DateAndTimes.count){
            return false
        }
        else{
            arr_DateAndTimes.remove(at: index)
            return true
        }
    }
    
    //rename the stitch
    func renameStitch(newName: String){
        name = newName
    }
    
    //getters
    func getStitchName() -> String{
        return name
    }
    
    func getDateAndTimeAt(at index: Int) -> DateAndTime? {
        if (index < 0 || index >= arr_DateAndTimes.count){
            return nil
        }
        return arr_DateAndTimes[index]
    }
    
    func getFirstDateAndTime() -> DateAndTime? {
        if (arr_DateAndTimes.count <= 0) {
            return nil
        }
        return arr_DateAndTimes[0]
    }
    
    //get size of date and times array
    func getSize() -> Int{
        return arr_DateAndTimes.count
    }
    
    //change the name of the stitch
    func updateStitchName(newName : String) {
        print("updating to \(newName)")
        name = newName
    }
    
}
