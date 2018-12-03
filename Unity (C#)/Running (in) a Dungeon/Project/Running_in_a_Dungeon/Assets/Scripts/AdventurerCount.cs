using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AdventurerCount : MonoBehaviour {

	public static int totalCount = 0;
	public static int deathCount = 0;
	static bool rerolled = false;

	public static float rate = .8f;

	public static int min = 2;
	public static int max = 5;
	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	public static void Reroll(){

		//go through the list of currently unlocked floors and reset the values
		for (int i = 0; i < DungeonList.curMaxFloor; i++){
			DungeonList.arr_corpsePerFloor[i] = 0;
		}
		totalCount = 0;
		deathCount = 0;
		//totalCount = Random.Range(min,max);
		// for (int i = 0; i < totalCount; i++){
		// 	float chance = Random.value;//Range(0.0f, 1.0f);
		// 	Debug.Log(chance);
		// 	if (chance < rate){
		// 		deathCount = deathCount +  1;
		// 	}
		// }

		//curFloor + 1 just in case you unlocked a new floor
		for (int curFloor = 0; curFloor < DungeonList.curMaxFloor+1; curFloor++){

			//get the difficulty of the current floor
			int curFloorDiff = DungeonList.arr_floorInfo[curFloor].curDifficulty;

			//how many adventurers entered the dungeon
			int numAdventurersOnThisFloor= Random.Range(min, max) + curFloorDiff;

			

			//the chance of death for the current floor
			float deathRate = 0.6f + (0.1f * curFloorDiff);

			
			
			//loop through the list of people who entered
			for (int potentialVictim = 0; potentialVictim < numAdventurersOnThisFloor; potentialVictim++){
				//random survival chance
				float chance = Random.value;

				//if the survival chance is less than the death chance, they die
				if (chance < deathRate){
					//adds the death to the number of corpses on the floor
					DungeonList.arr_corpsePerFloor[curFloor]++;
					//deathCount++;
				}

				

			}
			//a pity adventurer
			if (DungeonList.arr_corpsePerFloor[curFloor] == 0){
				Debug.Log("pity corpse");
				DungeonList.arr_corpsePerFloor[curFloor]++;
			}
			
			//total death count += number of deaths on that floor
			deathCount += DungeonList.arr_corpsePerFloor[curFloor];
				//total number of adventurers in dungeon += number of adventurers on this floor
			totalCount += numAdventurersOnThisFloor;

		}	//end of curFloor for loop
		
		//rerolled = true;
	}//end of Reroll function

}
