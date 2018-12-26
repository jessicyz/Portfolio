//
//  AddStitchToChartTableViewController.swift
//   Saiorse
//
//  Created by Jess Zhu on 12/3/18.
//  Copyright © 2018 Jess Zhu. All rights reserved.
//
//jessicyz@usc.edu
import UIKit

class AddStitchToChartTableViewController: UITableViewController {

    let reuseIdentifier: String = "AddStitchViewCell"
    var stitchModel : StitchesModel = StitchesModel.sharedInstance
    var chart : Chart?
    override func viewDidLoad() {
        super.viewDidLoad()
        

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return stitchModel.getSize()
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        // 1) Create cell
        let cell = tableView.dequeueReusableCell(withIdentifier: reuseIdentifier, for: indexPath)
        
        // 2) Configure the cell...
        if let stitch = stitchModel.getStitchAt(at: indexPath.row){
            cell.textLabel!.text = stitch.getStitchName()

            
        }
        else{
            cell.textLabel!.text = "No stitches"

        }
        
        // 3) Return cell
        return cell
    }
    

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
        
        if segue.identifier == "AddNumberTimesToChartStitch", let addNumStitchViewController = segue.destination as? AddEditStitchOnChartViewController{
            addNumStitchViewController.chart = chart
            if let indexPath = self.tableView.indexPathForSelectedRow{
                let selectedRow = indexPath.row
                //pass the stitch in numstitch form to the VC
                addNumStitchViewController.stitch = chart?.getStitchAt(at: selectedRow)
                
                //change title of VC
                addNumStitchViewController.navigationItem.title =  chart?.getStitchAt(at: selectedRow)?.getStitchName()
            }
            addNumStitchViewController.newChartStitch = true
        }
    }
 

}
