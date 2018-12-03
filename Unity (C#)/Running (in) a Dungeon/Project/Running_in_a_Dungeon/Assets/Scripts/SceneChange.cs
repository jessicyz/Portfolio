using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class SceneChange : MonoBehaviour {

	//delay for automatic transition
	public float delay = 0f;//440;

	//index of DungeonList array
	public int thisFloorNum = -1;

	//name of next scene
    //public FloorInfo nextLevel;
	public string nextLevel;
	public bool overrideAutomation = false;
	public string overrideRoomName = "StartMenu";





	//is there a door? if there is, you need to actually
	//go up to a door to transition
    public bool doorless = false;

    // Use this for initialization
    void Start () {
		//switch room after delay if there is one
		// if (delay > 0){
		// 	Debug.Log("gonna switch rooms automatically");
		// 	StartCoroutine(LoadLevelAfterDelay(delay));
		// }

		//if current floor index is < 0, you're on the results screen
		//and you'll want to transition to the first level
		if (thisFloorNum == -1){
			nextLevel = DungeonList.GetFloorNameWithDifficulty(0); //DungeonList.arr_floorInfo[0];
           

        }
        else if(thisFloorNum == -2)
        {
            nextLevel = DungeonList.dungeon_results.floorName;
        }
		//if you are on the last floor available, 
		//you'll want to transition to the results screen
		//if you're on the first floor and you only have that floor unlocked
		//curMaxFloor = 0
		//thisFloorNum + 1 = 1
		//so the next floor should be results
		else if (thisFloorNum + 1 >= DungeonList.curMaxFloor + 1){
			
			nextLevel = DungeonList.dungeon_upgrademenu.floorName;
		}
		//otherwise, transition to the next level
		else{
			nextLevel = DungeonList.GetFloorNameWithDifficulty(thisFloorNum + 1);
		}
	}
	
	// Update is called once per frame
	void Update () {
		//if doorless, pressing z will automatically transition
		if (delay > 0){
			delay -= Time.deltaTime;
		}
		if (doorless){
			checkKeyToNextDoorPressed();
		}
		
	}

	// IEnumerator LoadLevelAfterDelay(float delay)
    //  {
        
	// 	yield return new WaitForSeconds(delay);
    // 	LoadNextSceneIfUnlocked();
		
    //  }

	//if there is a door, this script will be on that door
	void OnTriggerStay2D(Collider2D target){
        if (target.gameObject.tag == "Player")
        {
			//Debug.Log("Player reached door");

			checkKeyToNextDoorPressed();
            
        }
	}

	//check if z is pressed
	void checkKeyToNextDoorPressed( ){
		if (delay > 0){
			return;
		}

		if (Input.GetKeyDown("z")){
			//Debug.Log("max number of floors: " + DungeonList.curMaxFloor);
			//Debug.Log("next floor name: " + nextLevel);
				//Debug.Log("Open Door to " + nextLevel.floorName);
				//StartCoroutine(LoadLevelAfterDelay(delay));
				LoadNextSceneIfUnlocked();
		}
	}

	void LoadNextSceneIfUnlocked(){

		if (!overrideAutomation){
			SceneManager.LoadScene(nextLevel);
			if (nextLevel == DungeonList.dungeon_results.floorName){
				AudioPlayer.Instance.PlayLaughSFX();
			}
		}
		else{
			SceneManager.LoadScene(overrideRoomName);
			if (overrideRoomName == DungeonList.dungeon_results.floorName){
				AudioPlayer.Instance.PlayLaughSFX();
			}
		}
		//AudioPlayer.Instance.SwitchClip();

		if (!doorless){
			AudioPlayer.Instance.PlayEnterPortalSFX();
		}

		
		

		// if (!nextLevel.unlocked){
		// 	SceneManager.LoadScene(DungeonList.dungeon_results.floorName);
		// }
		// else{
		// 	SceneManager.LoadScene(nextLevel.floorName);
		// }


	}


}
