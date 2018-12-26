//
//  AddChartViewController.swift
//   Saiorse
//
//  Created by Jess Zhu on 12/2/18.
//  Copyright © 2018 Jess Zhu. All rights reserved.
//

//jessicyz@usc.edu

import Foundation
import UIKit

class AddChartViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    var chartModel : ChartsModel = ChartsModel.sharedInstance
    
    var chart : Chart?
    
    let reuseIdentifier : String = "AddChartStitchesCell"
    
    
    @IBOutlet weak var lab_totalTime: UILabel!
    
    
    
    @IBOutlet weak var table_stitches: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
//        self.navigationItem.rightBarButtonItem = self.editButtonItem
        
        //dismiss when save or cancelled pressed
        
        NotificationCenter.default.addObserver(self, selector: #selector(loadList), name: NSNotification.Name(rawValue: "loadChartTimesTable"), object: nil)
    }
    
 
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if let chartCheck = chart{
            return chartCheck.getSize()
        }
        print("no chart")
        return 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: reuseIdentifier, for: indexPath) as! ChartStitchesTableViewCell
        
        
        if let curStitch = chart?.getStitchAt(at: indexPath.row){
            
            //Name    99  0.00s
            cell.lab_stitchName.text = curStitch.getStitchName()
            cell.lab_numberStitches.text = curStitch.getNumberTimesUsed().description
            cell.lab_totalStitchTime.text = curStitch.getTimeInMinuteSecond()
            
        }
        
        
        return cell
    }
    
    
    
    
    
    
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
        
        //if editing a chart stitch, pass the name of the stitch and tell the VC that it is not a new stitch
        if segue.identifier == "EditChartStitch", let editViewController = segue.destination as? AddEditStitchOnChartViewController{
            
            if let chart_ = chart{
                
                //send chart
                editViewController.chart = chart_
                if let indexPath = table_stitches.indexPathForSelectedRow{
                    let selectedRow = indexPath.row
                    //pass the stitch in numstitch form to the VC
                    editViewController.stitch = chart?.getStitchAt(at: selectedRow)

                    //change title of VC
                    editViewController.navigationItem.title =  chart?.getStitchAt(at: selectedRow)?.getStitchName()
                }
                editViewController.newChartStitch = false
                
            }
                //if making a new stitch, send the chart info to the stitch table
            else if segue.identifier == "AddChartStitch", let addStitchToChartViewController = segue.destination as? AddStitchToChartTableViewController{
                addStitchToChartViewController.chart = chart
            }
            
        }
        
        
        
        
    }
    
    
    
    @objc func loadList(){
        //update text and reload table
        
        lab_totalTime.text = Helper.helper.secondsToHourMinuteSecondString(s: chart?.getFinalTime() ?? 0)
        table_stitches.reloadData()

    }
}
