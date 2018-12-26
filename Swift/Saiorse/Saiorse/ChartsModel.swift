//
//  ChartsModel.swift
//   Saiorse
//
//  Created by Jess Zhu on 12/6/18.
//  Copyright © 2018 Jess Zhu. All rights reserved.
//
//jessicyz@usc.edu
import Foundation


class ChartsModel{
    private var arr_charts = [Chart]()
    
    // Swift Singleton pattern
    static let sharedInstance = ChartsModel()
    
    private(set) var currentIndex : Int? = 0;
    
    init(){
        
    }
    
    //get size of stitches array
    func getSize() -> Int{
        return arr_charts.count
    }
    
    //get Chart at index without changing the current index
    
    func getChartAt(at index: Int) -> Chart?{
        if (index < 0 || index >= arr_charts.count){
            return nil
        }
        return arr_charts[index]
    }
    
    //get chart at index and change the current index
    func chart(setIndexTo index: Int) -> Chart?{
        if (index < 0 || index >= arr_charts.count){
            return nil
        }
        
        currentIndex = index
        return arr_charts[index]
        
    }
    
    //append a stitch
    
    func append(name chart: String, arr_numStitch: [NumberStitchesUsed]){
        //check if it already exists. if it does, don't do anything (for now. May do something different later)
        if var checkChartExists = findChart(chartName: chart){
            
        }
        else{
            //arr_stitches.append(Stitch(stitchName: stitch, time: time, date: date))
            arr_charts.append(Chart(name_: chart, arr_numStitch: arr_numStitch))
        }
    }
    
    func append(name chart: String){
        if var checkChartExists = findChart(chartName: chart){
            
        }
        else{
            arr_charts.append(Chart(name_: chart))
        }
        
    }
    
    //find a chart exists
    func findChart(chartName : String ) -> Chart? {
        
        for chart in arr_charts {
            if (chart.getChartName() == chartName){
                return chart
            }
        }
        
        return nil
    }
    
    
    func deleteChart(at index: Int){
        if (index >= 0 && index < arr_charts.count){
            arr_charts.remove(at: index)
        }
    }
    
    func deleteChart(chartName: String){
        
        //go through array to search for a stitch with that name
        for index in 0...arr_charts.count{
            //remove the stitch if it has that name
            if arr_charts[index].getChartName() == chartName{
                
                arr_charts.remove(at: index)
                
                return
            }
        }
    }
    
//    func updateStitchNameInCharts(from oldName: String, to newName: String){
//
//    }
    
    
}
