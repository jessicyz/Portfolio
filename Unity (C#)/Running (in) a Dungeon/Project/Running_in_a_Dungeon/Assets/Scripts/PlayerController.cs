using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerController : MonoBehaviour {

    public Vector2 moving = new Vector2();

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
        //reset moving values to 0
        moving.x = moving.y = 0;

    //check if keys are pressed
        if (Input.GetKey("right"))
        {
            //if moving right, x movement is positive
            moving.x = 1;

        }
        else if (Input.GetKey("left"))
        {
            //if moving left, x movement is negative
            moving.x = -1;
        }

        if (Input.GetKey("up") || Input.GetKey("space"))//(Input.GetKeyDown("up") || Input.GetKeyDown("space"))
        {
            //if moving up, y movement is positive
            moving.y = 1;

        }
        else if (Input.GetKey("down"))
        {
            //not using it, but just in case
            //if moving down, y movement is negative
            moving.y = -1;
        }


        //toggle tutorial on and off.
		//is t because tutorial starts with t 
		//and it's far away from the action keys so it isn't accidentally toggled
		if ( Input.GetKeyDown("t") ){
			TutorialText.tutorialOn = !TutorialText.tutorialOn;
		}


    }
}
