//
//  StitchTableViewController.swift
//   Saiorse
//
//  Created by Jess Zhu on 11/23/18.
//  Copyright © 2018 Jess Zhu. All rights reserved.
//
//jessicyz@usc.edu

import UIKit

class StitchTableViewController: UITableViewController {
    
    var model : StitchesModel = StitchesModel.sharedInstance
    
    let reuseIdentifier = "StitchTableCell"

    override func viewDidLoad() {
        super.viewDidLoad()


        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        self.navigationItem.leftBarButtonItem = self.editButtonItem
        
        NotificationCenter.default.addObserver(self, selector: #selector(loadList), name: NSNotification.Name(rawValue: "loadStitchTable"), object: nil)
        
        
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        
        return model.getSize()
    }


    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {

        // Configure the cell...
        
        // 1) Create cell
        let cell = tableView.dequeueReusableCell(withIdentifier: reuseIdentifier, for: indexPath)
        
        // 2) Configure the cell...
        if let stitch = model.getStitchAt(at: indexPath.row){
            cell.textLabel!.text = stitch.getStitchName()
            if let time = stitch.getFirstDateAndTime(){
                cell.detailTextLabel!.text = time.getTimeStringTwoDecimals() + "s"
            }
            else{
                cell.detailTextLabel!.text = "0.00s"
            }
            
        }
        else{
            cell.textLabel!.text = "No stitches"
            cell.detailTextLabel!.text = "0.00s"
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


    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            model.deleteStitch(at: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: .fade)
            
        }
        
//        else if editingStyle == .insert {
//            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
//        }
    }


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
        
        // Checking to see if the next view controller is to the list of times for the particular stitch tapped
        if segue.identifier == "ViewStitch", let stitchTimesViewController = segue.destination as? StitchTimesTableViewController{

                if let indexPath = tableView.indexPathForSelectedRow{
                    let selectedRow = indexPath.row
                    //pass the stitch to the VC
                    stitchTimesViewController.stitch = model.getStitchAt(at: selectedRow)
                    //change title of VC
                    stitchTimesViewController.navigationItem.title =  model.getStitchAt(at: selectedRow)?.getStitchName()
                }
            stitchTimesViewController.tableView.reloadData()
                
                
            }
        
        else if segue.identifier == "AddStitch", let addViewController = segue.destination as? AddStitchViewController{
            
            
            
            //say that it's a new stitch
            addViewController.newStitch = true
            
            
        }
        
  
            
        
    }
    
    @objc func loadList(){
        //load data here
        self.tableView.reloadData()
    }
 

}
