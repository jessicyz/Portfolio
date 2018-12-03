using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class UniversalUpgradeScript : MonoBehaviour {


    public GameObject SkullUILv2;
    public GameObject SkullUILv3;
    public GameObject SkullUILv4;

    public static int tutoriallv = 1;
    public static int flooronelv = 0;
    public static int floortwolv= 0;
    public static int shootingspikelv = 0;


     public  Text tutorialTargettext;
     public Text flooroneTargettext;
     public  Text floortwoTargettext;
     public  Text shootingspikeTargettext;

     bool canupgradetutorial = false;
     bool canupgradefloorone = false;
     bool canupgradefloortwo = false;
     bool canupgradeshootingspike = false;

    string floor0 = "floor0";
    string floor1 = "floor1";
    string floor2 = "floor2";
    string floor3 = "floor3";

    public static int floor0InitialUpCost = 50;
    public static int floor1UnlockCost = 100;
    public static int floor2UnlockCost = 125;
    public static int floor3UnlockCost = 150;
    

    bool setdefaulttext = true;
    public static bool reset = false;

    
  

	// Use this for initialization
	void Start () {
       
        
        /**
		if(setdefaulttext)
        {
            targerttext.text = "Locked";
            setdefaulttext = false;
        }
    **/
	}
	
	// Update is called once per frame
	void Update () {
        if(reset == true)
        {
            ResetUpgradeScript();
            reset = false;
        }

        if (tutoriallv == 1 && Money.amount > floor0InitialUpCost)
        {
            canupgradetutorial = true;
            Debug.Log("Money:" + Money.amount.ToString());
        }
        else if (tutoriallv == 2 && Money.amount > (int)(floor0InitialUpCost * 1.5))
        {
            canupgradetutorial = true;
            Debug.Log("Money:" + Money.amount.ToString());
        }
        else
        {
            canupgradetutorial = false;
        }


        if(flooronelv == 0 && Money.amount>floor1UnlockCost)
        {
            canupgradefloorone = true;
        }
        else if(flooronelv == 1 && Money.amount>(int)(floor1UnlockCost*1.5))
        {
            canupgradefloorone = true;
        }
        else if(flooronelv ==2 && Money.amount > (int)(floor1UnlockCost*1.5*1.5))
        {
            canupgradefloorone = true;
        }
        else
        {
            canupgradefloorone = false;
        }



        if(floortwolv == 0 && Money.amount > floor2UnlockCost)
        {
            canupgradefloortwo = true;
        }
        else if(floortwolv == 1 && Money.amount > (int)(floor2UnlockCost*1.5))
        {
            canupgradefloortwo = true;
        }
        else if(floortwolv == 2 && Money.amount > (int)(floor2UnlockCost*1.5*1.5))
        {
            canupgradefloortwo = true;
        }
        else
        {
            canupgradefloortwo = false;
        }


        if (shootingspikelv == 0 && Money.amount > floor3UnlockCost)
        {
            canupgradeshootingspike = true;
        }
        else if (shootingspikelv == 1 && Money.amount > (int)(floor3UnlockCost * 1.5))
        {
            canupgradeshootingspike = true;
        }
        else if(shootingspikelv == 2 && Money.amount> (int)(floor3UnlockCost*1.5*1.5))
        {
            canupgradeshootingspike = true;
        }
        else 
        {
            canupgradeshootingspike = false;
        }
    }


    public void UnlockOrUpgrade()
    {
        Debug.Log("button is:" + gameObject.tag);
        Debug.Log("canUpgradeTutorial: " + canupgradetutorial);
        Debug.Log("canUpgradeFloorOne: " + canupgradefloorone);
        Debug.Log("canFloorTwo: " + canupgradefloortwo);
        Debug.Log("canFloorThree: " + canupgradeshootingspike);
        Debug.Log("tutorialLv is:" + tutoriallv.ToString());
        if (canupgradetutorial && gameObject.tag == "tutorialUpgradeButton")
        {
            Text changedbuttontext = gameObject.GetComponentInChildren<Text>();
            if (changedbuttontext != null)
            {
                changedbuttontext.text = "Upgrade";
            }

            if (tutoriallv == 0)
            {
                DungeonList.curMaxFloor++;
                Money.amount = Money.amount - 100;
            }
            else
            {
                UpgradeScene(floor0, tutoriallv);
            }

            if (tutoriallv < 3)
            {
                tutoriallv++;
            }

        }
        else if(canupgradefloorone&&gameObject.tag == "flooroneUpgradeButton")
        {
            Text changedbuttontext = gameObject.GetComponentInChildren<Text>();
            if (changedbuttontext != null)
            {
                changedbuttontext.text = "Upgrade";
            }

            if(flooronelv == 0)
            {
                DungeonList.curMaxFloor++;
                Money.amount = Money.amount - floor1UnlockCost;
            }
            else
            {
                UpgradeScene(floor1, flooronelv);
            }
            
            if(flooronelv<3)
            {
                flooronelv++;
            }
            
        }
        // JESS: && DungeonList.curMaxFloor >= 1 is temporary for now, unless it works 
        else if(canupgradefloortwo&&gameObject.tag == "floortwoUpgradeButton" && DungeonList.curMaxFloor >= 1)
        {
            Text changedbuttontext = gameObject.GetComponentInChildren<Text>();
            if (changedbuttontext != null)
            {
                changedbuttontext.text = "Upgrade";
            }

            if(floortwolv == 0)
            {
                DungeonList.curMaxFloor++;
                Money.amount = Money.amount - floor2UnlockCost;

            }
            else
            {
                UpgradeScene(floor2, floortwolv);

            }
            
           

            if(floortwolv <3)
            {
                floortwolv++;
            }
            
        }

        else if(canupgradeshootingspike && gameObject.tag == "shootingspikeUpgradeButton" && DungeonList.curMaxFloor >= 2)
        {
            Text changedbuttontext = gameObject.GetComponentInChildren<Text>();
            if (changedbuttontext != null)
            {
                changedbuttontext.text = "Upgrade";
            }

            if(shootingspikelv == 0)
            {
                DungeonList.curMaxFloor++;
                Money.amount = Money.amount - floor3UnlockCost;
            }
            else
            {
                UpgradeScene(floor3, shootingspikelv);
            }
            

            if(shootingspikelv<3)
            {
                shootingspikelv++;
            }
           
         
        }
     
    }

    public void UpgradeScene(string whichfloor, int currentdifflevel)
    {
        FloorInfo newfloor = new FloorInfo("", 0, 0, true);
        if(whichfloor == floor0)
        {
            if (currentdifflevel + 1 == 1)
            {
                //newfloor.floorName = "scene_floor_0";
            }
            else if (currentdifflevel + 1 == 2 && DungeonList.arr_floorInfo[0].curDifficulty == 0)
            {
                //newfloor.floorName = "scene_floor_0_1";
                DungeonList.arr_floorInfo[0].curDifficulty += 1;
                Money.amount = Money.amount - floor0InitialUpCost;
            }
            else if (currentdifflevel + 1 == 3 && DungeonList.arr_floorInfo[0].curDifficulty == 1)
            {
                //newfloor.floorName = "scene_floor_0_2";
                DungeonList.arr_floorInfo[0].curDifficulty += 1;
                Money.amount = Money.amount - (int)(floor0InitialUpCost*1.5);
            }
            //JESS: Temp fix
            else
            {
                //newfloor.floorName = "scene_floor_0_2";
                DungeonList.arr_floorInfo[0].curDifficulty = 2;
            }
        }
        else if(whichfloor == floor1)
        {
            if (currentdifflevel + 1 == 1 )
            {
                //newfloor.floorName = "scene_floor_1";
            }
            else if (currentdifflevel + 1 == 2 &&  DungeonList.arr_floorInfo[1].curDifficulty == 0 )
            {
                //newfloor.floorName = "scene_floor_1_1";
                DungeonList.arr_floorInfo[1].curDifficulty += 1;
                Money.amount = Money.amount -(int)(floor1UnlockCost*1.5);
            }
            else if (currentdifflevel + 1 == 3 &&  DungeonList.arr_floorInfo[1].curDifficulty == 1 )
            {
                //newfloor.floorName = "scene_floor_1_2";
                DungeonList.arr_floorInfo[1].curDifficulty += 1;
                Money.amount = Money.amount - (int)(floor1UnlockCost*1.5*1.5);
            }
            //JESS: Temp fix
            else{
                //newfloor.floorName = "scene_floor_1_2";
                DungeonList.arr_floorInfo[1].curDifficulty = 2;
            }

            //Money.amount = Money.amount - 100;
            //DungeonList.arr_floorInfo[1] = newfloor;
        }
        else if(whichfloor == floor2)
        {
            if (currentdifflevel + 1 == 1)
            {
                //newfloor.floorName = "scene_floor_2";
            }
            else if (currentdifflevel + 1 == 2  &&  DungeonList.arr_floorInfo[2].curDifficulty == 0 )
            {
               // newfloor.floorName = "scene_floor_2_1";
               DungeonList.arr_floorInfo[2].curDifficulty += 1;
                Money.amount = Money.amount - (int)(floor2UnlockCost*1.5);
            }
            else if (currentdifflevel + 1 == 3 &&  DungeonList.arr_floorInfo[2].curDifficulty == 1 )
            {
                //newfloor.floorName = "scene_floor_2_2";
                DungeonList.arr_floorInfo[2].curDifficulty += 1;
                Money.amount = Money.amount - (int)(floor2UnlockCost*1.5*1.5);
            }
            //JESS: Temp fix
            else{
                //newfloor.floorName = "scene_floor_2_2";
                DungeonList.arr_floorInfo[2].curDifficulty = 2;
            }
            //DungeonList.arr_floorInfo[2] = newfloor;

            
        }
        else if(whichfloor == floor3  )
        {
            if (currentdifflevel + 1 == 1)
            {
                //newfloor.floorName = "scene_floor_3";
            }
            else if (currentdifflevel + 1 == 2 &&  DungeonList.arr_floorInfo[3].curDifficulty == 0)
            {
                //newfloor.floorName = "scene_floor_3_1";
                DungeonList.arr_floorInfo[3].curDifficulty += 1;
                Money.amount = Money.amount - (int)(floor3UnlockCost*1.5);
            }
            else if (currentdifflevel + 1 == 3 &&  DungeonList.arr_floorInfo[3].curDifficulty == 1)
            {
                //newfloor.floorName = "scene_floor_3_2";
                DungeonList.arr_floorInfo[3].curDifficulty += 1;
                Money.amount = Money.amount - (int)(floor3UnlockCost*1.5*1.5);
            }
            //JESS: Temp fix
            else{
                //newfloor.floorName = "scene_floor_3_2";
                DungeonList.arr_floorInfo[3].curDifficulty = 2;
            }
            //DungeonList.arr_floorInfo[3] = newfloor;
           


        }


      
       
    }
    public void ResetUpgradeScript()
    {
        tutoriallv = 1;
        flooronelv = 0;
        floortwolv = 0;
        shootingspikelv = 0;

         canupgradetutorial = true;
         canupgradefloorone = true;
         canupgradefloortwo = true;
         canupgradeshootingspike = true;



    //upgradecost = 100;

     setdefaulttext = true;

}
}
