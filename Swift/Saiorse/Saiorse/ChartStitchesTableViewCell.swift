//
//  ChartStitchesTableViewCell.swift
//   Saiorse
//
//  Created by Jess Zhu on 12/7/18.
//  Copyright © 2018 Jess Zhu. All rights reserved.
//
//jessicyz@usc.edu
import UIKit

class ChartStitchesTableViewCell: UITableViewCell {

    //name of stitch
    @IBOutlet weak var lab_stitchName: UILabel!
    
    //number of times used
    @IBOutlet weak var lab_numberStitches: UILabel!
    //total time it takes to do all those stitches
    @IBOutlet weak var lab_totalStitchTime: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
