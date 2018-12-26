//
//  DateAndTime.swift
//   Saiorse
//
//  Created by Jess Zhu on 11/23/18.
//  Copyright © 2018 Jess Zhu. All rights reserved.
//
//jessicyz@usc.edu
import Foundation

struct DateAndTime{
    let averageTime: Double
    let date: Date
    
    
    let dateString: String
    
    init(newTime: Double, newDate: Date){
        averageTime = newTime
        date = newDate
        
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "MMM dd, yyyy"
        
        dateString = dateFormatter.string(from: date)
        
        
    }
    
    //get average time
    func getTime() -> Double{
        return averageTime
    }
    
    //get average time as string
    func getTimeStringTwoDecimals() -> String{
        return String(format: "%.2f", averageTime)
    }
    
    //get date
    func getDate() -> Date{
        return date
    }
    
    //get date string
    func getDateString() -> String{
        return dateString
    }
    
    //date to date string
    
    func dateToDateString(newDate: Date) -> String{
        var toReturn : String
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "MMM dd, yyyy"
        
        toReturn = dateFormatter.string(from: newDate)
        
        return toReturn
    }
}
