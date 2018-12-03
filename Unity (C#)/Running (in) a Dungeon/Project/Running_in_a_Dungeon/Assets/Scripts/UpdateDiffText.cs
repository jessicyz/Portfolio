using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class UpdateDiffText : MonoBehaviour {

    //public Text tutorialTargettext;
    //public Text flooroneTargettext;
    //public Text floortwoTargettext;
    // public Text shootingspikeTargettext;

    public Text floor0Price;
    public Text floor1Price;
    public Text floor2Price;
    public Text floor3Price;

    public Text button1text;
    public Text button2text;
    public Text button3text;
    public Text button4text;

    public GameObject SkullUILv2;
    public GameObject SkullUILv3;
    public GameObject SkullUILv4;


    //public List<GameObject> SkullsLv1;
    public List<GameObject> SkullsLv1;
    public List<GameObject> SkullsLv2;
    public List<GameObject> SkullsLv3;
    public List<GameObject> SkullsLv4;

    Color redcolor;
    

    
    // Use this for initialization
    void Start () {

        redcolor = SkullUILv2.GetComponent<Image>().color;
       
        


        
		
	}

    
	
	// Update is called once per frame
	void Update () {
       

            //Button text
        if(UniversalUpgradeScript.tutoriallv == 0)
        {
            button1text.text = "Unlock";
        }
        else if(UniversalUpgradeScript.tutoriallv>0)
        {
            button1text.text = "Upgrade";
        }

        if(UniversalUpgradeScript.flooronelv == 0)
        {
            button2text.text = "Unlock";
        }
        else if(UniversalUpgradeScript.flooronelv>0)
        {
            button2text.text = "Upgrade";
        }

        if(UniversalUpgradeScript.floortwolv == 0)
        {
            button3text.text = "Unlock";
        }
        else if(UniversalUpgradeScript.floortwolv > 0 )
        {
            button3text.text = "Upgrade";
        }


        if(UniversalUpgradeScript.shootingspikelv == 0)
        {
            button4text.text = "Unlock";
        }
        else if(UniversalUpgradeScript.shootingspikelv > 0)
        {
            button4text.text = "Upgrade";
        }



            if (UniversalUpgradeScript.tutoriallv == 0)
            {
            //tutorialTargettext.text = "Locked";
            // SkullsLv1[0].GetComponent<Image>().color = Color.white;
            SkullsLv1[0].GetComponent<Image>().color = redcolor;
            SkullsLv1[1].GetComponent<Image>().color = redcolor;
            SkullsLv1[2].GetComponent<Image>().color = redcolor;

                
                
            }
            else if (UniversalUpgradeScript.tutoriallv == 1)
            {
            //tutorialTargettext.text = "Lv1";
            //SkullsLv1[0].GetComponent<Image>().color = Color.white;
            //SkullsLv1[1].GetComponent<Image>().color = Color.white;
            SkullsLv1[0].GetComponent<Image>().color = Color.white;
            SkullsLv1[1].GetComponent<Image>().color = redcolor;
            SkullsLv1[2].GetComponent<Image>().color = redcolor;
            floor0Price.text = 50.ToString()+"G";



        }
            else if (UniversalUpgradeScript.tutoriallv == 2)
            {
            // tutorialTargettext.text = "Lv2";
            //SkullsLv1[0].GetComponent<Image>().color = Color.white;
            //SkullsLv1[1].GetComponent<Image>().color = Color.white;
            //SkullsLv1[2].GetComponent<Image>().color = Color.white;
            SkullsLv1[0].GetComponent<Image>().color = Color.white;
            SkullsLv1[1].GetComponent<Image>().color = Color.white;
            SkullsLv1[2].GetComponent<Image>().color = redcolor;
            floor0Price.text = ((int)(50 * 1.5)).ToString() + "G";

        }
            else if(UniversalUpgradeScript.tutoriallv == 3)
            {
            //do nothing
            //tutorialTargettext.text = "Lv3";
            SkullsLv1[0].GetComponent<Image>().color = Color.white;
            SkullsLv1[1].GetComponent<Image>().color = Color.white;
            SkullsLv1[2].GetComponent<Image>().color = Color.white;
            floor0Price.text = "MAX";
        }

 

            if (UniversalUpgradeScript.flooronelv == 0)
            {
            //flooroneTargettext.text = "Locked";
            SkullUILv2.GetComponent<Image>().color = redcolor;
            SkullsLv2[1].GetComponent<Image>().color = redcolor;
            SkullsLv2[2].GetComponent<Image>().color = redcolor;
            floor1Price.text = 100.ToString() + "G";

        }
            else if (UniversalUpgradeScript.flooronelv == 1)
            {
               // flooroneTargettext.text = "Lv1";
                SkullUILv2.GetComponent<Image>().color = Color.white;
            SkullsLv2[1].GetComponent<Image>().color = redcolor;
            SkullsLv2[2].GetComponent<Image>().color = redcolor;
            floor1Price.text = ((int)(100 * 1.5)).ToString() + "G";
                
            }
            else if (UniversalUpgradeScript.flooronelv == 2)
            {
            // flooroneTargettext.text = "Lv2";
            SkullUILv2.GetComponent<Image>().color = Color.white;
            SkullsLv2[1].GetComponent<Image>().color = Color.white;
            SkullsLv2[2].GetComponent<Image>().color = redcolor;
            floor1Price.text = ((int)(100 * 1.5 * 1.5)).ToString() + "G";
                
            }
            else if(UniversalUpgradeScript.flooronelv == 3)
            {
            //do nothing
            // flooroneTargettext.text = "Lv3";
            SkullUILv2.GetComponent<Image>().color = Color.white;
            SkullsLv2[1].GetComponent<Image>().color = Color.white;
            SkullsLv2[2].GetComponent<Image>().color = Color.white;
            floor1Price.text = "MAX";
            }
      
      

            if (UniversalUpgradeScript.floortwolv == 0)
            {
            //floortwoTargettext.text = "Locked";
            SkullUILv3.GetComponent<Image>().color = redcolor;
            SkullsLv3[1].GetComponent<Image>().color = redcolor;
            SkullsLv3[2].GetComponent<Image>().color = redcolor;
            floor2Price.text = 125.ToString() + "G";


            }
            else if (UniversalUpgradeScript.floortwolv == 1)
            {
                //floortwoTargettext.text = "Lv1";
                SkullUILv3.GetComponent<Image>().color = Color.white;
            SkullsLv3[1].GetComponent<Image>().color = redcolor;
            SkullsLv3[2].GetComponent<Image>().color = redcolor;
            floor2Price.text = ((int)(125 * 1.5)).ToString() + "G";
            
            }
            else if (UniversalUpgradeScript.floortwolv == 2)
            {
            //floortwoTargettext.text = "Lv2";
            SkullUILv3.GetComponent<Image>().color = Color.white;
            SkullsLv3[1].GetComponent<Image>().color = Color.white;
            SkullsLv3[2].GetComponent<Image>().color = redcolor;
            floor2Price.text = ((int)(125 * 1.5 * 1.5)).ToString() + "G";
                
            }
            else if(UniversalUpgradeScript.floortwolv == 3)
            {
            //do nothing
            //floortwoTargettext.text = "Lv3";
            SkullUILv3.GetComponent<Image>().color = Color.white;
            SkullsLv3[1].GetComponent<Image>().color = Color.white;
            SkullsLv3[2].GetComponent<Image>().color = Color.white;
            floor2Price.text = "MAX";
            }
       

      

            if (UniversalUpgradeScript.shootingspikelv == 0)
            {
            //shootingspikeTargettext.text = "Locked";
            SkullsLv4[2].GetComponent<Image>().color = redcolor;
            SkullsLv4[1].GetComponent<Image>().color = redcolor;
            SkullUILv4.GetComponent<Image>().color = redcolor;
            floor3Price.text = 150.ToString() + "G";


            }
            else if (UniversalUpgradeScript.shootingspikelv == 1)
            {
               // shootingspikeTargettext.text = "Lv1";
                SkullUILv4.GetComponent<Image>().color = Color.white;
            SkullsLv4[2].GetComponent<Image>().color = redcolor;
            SkullsLv4[1].GetComponent<Image>().color = redcolor;

            floor3Price.text = ((int)(150 * 1.5)).ToString() + "G";
               
            }
            else if (UniversalUpgradeScript.shootingspikelv == 2)
            {
                //shootingspikeTargettext.text = "Lv2";
                SkullsLv4[1].GetComponent<Image>().color = Color.white;
            SkullUILv4.GetComponent<Image>().color = Color.white;
            SkullsLv4[2].GetComponent<Image>().color = redcolor;
            
            floor3Price.text = ((int)(150 * 1.5 * 1.5)).ToString() + "G";

        }
            else if(UniversalUpgradeScript.shootingspikelv == 3)
            {
            //do nothing
               // shootingspikeTargettext.text = "Lv3";
                SkullsLv4[2].GetComponent<Image>().color = Color.white;
                SkullsLv4[1].GetComponent<Image>().color = Color.white;
                SkullUILv4.GetComponent<Image>().color = Color.white;
            floor3Price.text = "MAX";

        }
       




    }
}
