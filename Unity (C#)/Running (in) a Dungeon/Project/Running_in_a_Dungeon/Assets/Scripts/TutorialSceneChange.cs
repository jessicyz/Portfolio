using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using UnityEngine.SceneManagement;
public class TutorialSceneChange : MonoBehaviour {






	//index of Tutorial Dungeon list array. 
	//Set this to be -1 if you want the next floor to be the tutorial
	public int thisFloorNum = -1;

	//name of next scene
    public string nextLevel = "scene_results";


    // Use this for initialization
    void Start () {

	}
	
	// Update is called once per frame
	void Update () {
		//if doorless, pressing z will automatically transition
		
	}

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
		if (Input.GetKeyDown("z")){

				LoadNextSceneIfAnyLeft();
		}
	}

	void LoadNextSceneIfAnyLeft(){

		if (thisFloorNum + 1 < DungeonList.maxNumTutorialFloors){
			SceneManager.LoadScene( DungeonList.arr_tutorialFloorNames[thisFloorNum + 1] );
		}
		else{
			SceneManager.LoadScene(nextLevel);
		}
		AudioPlayer.Instance.PlayEnterPortalSFX();
		


	}
}
