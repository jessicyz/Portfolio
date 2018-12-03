using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FloorInfo {

	public string floorName = "scene_floor_";
	public int floorNumber = -1;
	public int goldToUnlock = 0;
	public bool unlocked = false;
	public int curDifficulty = 0;



	public FloorInfo(){

	}



	public FloorInfo(string name, int num, int gold, bool unlock)
	{
		floorName = name;
		floorNumber = num;
		goldToUnlock = gold;
		unlocked = unlock;
		
	}
		


	// // Use this for initialization
	// void Start () {
		
	// }
	
	// // Update is called once per frame
	// void Update () {
		
	// }



}
