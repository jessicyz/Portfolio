using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using UnityEngine.SceneManagement;

public class IntroScreenChange : MonoBehaviour {

	public int introIndex = -1;
	public int introPart = 1;
	public string nextFloor = DungeonList.dungeon_startMenu.floorName;

	// Use this for initialization
	void Start () {
		//Debug.Log("Start intro screen change");
		if (introPart == 1){
			if (introIndex + 1 >= DungeonList.maxNumIntro1Scenes ){
				nextFloor = DungeonList.arr_tutorialFloorNames[0];
			}
			else{
				nextFloor = DungeonList.arr_intro1FloorNames[introIndex+ 1];
			}
		}
		else{
			if (introIndex + 1 >= DungeonList.maxNumIntro2Scenes){
				nextFloor = DungeonList.GetFloorNameWithDifficulty(0); //DungeonList.arr_floorInfo[0].floorName;
			}
			else{
				nextFloor = DungeonList.arr_intro2FloorNames[introIndex + 1];
			}
		}
		
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyUp("z")){
			Debug.Log("Pressed Z to " + nextFloor);
			SceneManager.LoadScene(nextFloor);
			// AudioPlayer.Instance.PlayEnterPortalSFX();
		}
		
	}
}
