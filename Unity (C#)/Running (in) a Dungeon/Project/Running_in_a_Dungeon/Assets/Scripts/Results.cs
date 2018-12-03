using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class Results : MonoBehaviour {

	Text txt;
	string numEntered;
	string numDied;
	//bool newFloorUnlocked;
	string nextFloorInfo;
	static bool rerolled = false;
	// Use this for initialization
	void Start () {
		txt = GetComponent<Text>();
		AdventurerCount.Reroll();
		rerolled = true;
		//newFloorUnlocked = DungeonList.CheckIfNewLevelUnlocked();
	}
	
	// Update is called once per frame
	void Update () {
		if (rerolled){
			numEntered = AdventurerCount.totalCount + " entered";
			numDied = AdventurerCount.deathCount + " died";
			// if (newFloorUnlocked){
			// 	nextFloorInfo = "NEW FLOOR RENOVATED";
			// }
			// else{
			// 	nextFloorInfo = DungeonList.HowMuchUntilNextFloor();
			// }
			
			txt.text = numEntered + "\n" + numDied; // + "\n" + nextFloorInfo;

		}
		rerolled = false;


	}
}
