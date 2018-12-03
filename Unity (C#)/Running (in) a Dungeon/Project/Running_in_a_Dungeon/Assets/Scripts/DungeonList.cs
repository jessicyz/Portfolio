using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public static class DungeonList  {

	//index of the current lowest floor
	public static int curMaxFloor = 0;

	public const int maxNumFloors = 4;
	public static FloorInfo[] arr_floorInfo = new FloorInfo[maxNumFloors];
	public static FloorInfo dungeon_results = new FloorInfo("scene_ResultsScreen", -1, 0, true);
    public static FloorInfo dungeon_upgrademenu = new FloorInfo("scene_upgrademenu", -2, 0, true);

	public static FloorInfo dungeon_gameOver = new FloorInfo("scene_gameOver", -3, 0, true);
	public static int[] arr_corpsePerFloor = new int[maxNumFloors];
	public static FloorInfo dungeon_startMenu = new FloorInfo("StartMenu", -4, 0, true);


	public const int maxNumTutorialFloors = 3;
	public static string[] arr_tutorialFloorNames = new string[maxNumTutorialFloors];


	public const int maxNumIntro1Scenes = 7;
	public static string[] arr_intro1FloorNames = new string[maxNumIntro1Scenes];

	public const int maxNumIntro2Scenes = 3;
	public static string[] arr_intro2FloorNames = new string[maxNumIntro2Scenes];

	static DungeonList(){



		for (int i = 0; i < maxNumTutorialFloors; i++){
			string floorName = "tutorial_" + (i+1).ToString();
			arr_tutorialFloorNames[i] = floorName;
		}

		//populate the first floor
		arr_corpsePerFloor[0] = 2;
		// p sure the default value is 0, but just in case
		for (int i = 1; i < maxNumFloors; i++){
			arr_corpsePerFloor[i] = 0;
		}

		arr_intro1FloorNames[0] = "intro_1";
		for (int i = 1; i < maxNumIntro1Scenes; i++){
			string floorName = "intro_1_" + (i).ToString();
			arr_intro1FloorNames[i] = floorName;
		}

		//arr_intro2FloorNames[0] = "intro_2";
		for (int i = 0; i < maxNumIntro2Scenes; i++){
			string floorName = "intro_2_" + (i).ToString();
			arr_intro2FloorNames[i] = floorName;
		}



		//make the first floor
		//unlocked by default
		arr_floorInfo[0] = new FloorInfo("scene_floor_0", 0, 0, true); //Floor_0/
		//the next amount of gold (starting amount)
		float nextAmountOfGold = 30.0f;
		float scaleAmount = 1.5f;
		
		for (int i = 1; i < maxNumFloors; i++){
			string floorName = "scene_floor_" + i.ToString(); // "Floor_" + i.ToString() + 
			int floorNum = i;
			//floor 1: 30 + (30*1.5) = 75
			//floor 2: 75 + (75*1.5) = 187
			//floor 3: 187 + (187 * 1.5) = 467
			//floor 4: 467 + (467 * 1.5) = 1167
			//floor 5: 1167 + (1167 * 1.5) = 2917

			nextAmountOfGold = ( nextAmountOfGold + (nextAmountOfGold * scaleAmount ) );
			int goldToUnlock = (int)nextAmountOfGold;

			arr_floorInfo[i] = new FloorInfo(floorName, floorNum, goldToUnlock, false);
		}
	}



	public static bool CheckIfNewLevelUnlocked(){
		//if your'e already on the last floor, return false
		//if ( curMaxFloor + 1 >= maxNumFloors ) return false;

	//if you reached the amount of gold needed to unlock the new floor
	//unlock it, increase curent max floor counter, and return true
		//if ( Money.amount > arr_floorInfo[curMaxFloor + 1].goldToUnlock ){
			//arr_floorInfo[curMaxFloor + 1].unlocked = true;

			//automatic money deduction for now
			//Money.amount -= arr_floorInfo[curMaxFloor + 1].goldToUnlock;

			//curMaxFloor++;

			
			//return true;
		//}

		return false;




	}

	public static string HowMuchUntilNextFloor(){


        /**
if (curMaxFloor + 1 >= maxNumFloors){
return "No more renovations can be done";
}
else{
int amountLeft = arr_floorInfo[curMaxFloor + 1].goldToUnlock - Money.amount;
return amountLeft.ToString() + "G left until next floor";
}
**/
        return "";
	}
	public static string GetFloorNameWithDifficulty( int index ){
		return arr_floorInfo[index].floorName + "_" + arr_floorInfo[index].curDifficulty;
	}
	

	public static void ResetLevels(){
				//make the first floor
		//unlocked by default
		
		//the next amount of gold (starting amount)
		float nextAmountOfGold = 30.0f;
		float scaleAmount = 1.5f;
		
		for (int i = 1; i < maxNumFloors; i++){

			nextAmountOfGold = ( nextAmountOfGold + (nextAmountOfGold * scaleAmount ) );
			//change back to difficulty 0
			arr_floorInfo[i].curDifficulty = 0;
			arr_floorInfo[i].goldToUnlock = (int)nextAmountOfGold;
			arr_floorInfo[i].unlocked = false;
		}

		arr_floorInfo[0].curDifficulty = 0;
		arr_floorInfo[0].goldToUnlock = 0;
		arr_floorInfo[0].unlocked = true;

		curMaxFloor = 0;
	}


}

