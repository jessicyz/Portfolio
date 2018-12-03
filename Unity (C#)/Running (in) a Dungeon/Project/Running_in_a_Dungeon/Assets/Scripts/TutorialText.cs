using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class TutorialText : MonoBehaviour {
	TextMesh txt;
	[TextArea]
	public string displayText = "";


	public static bool tutorialOn = true;

	// Use this for initialization
	void Start () {
		txt = GetComponent<TextMesh>();
		
		
	}
	
	// Update is called once per frame
	void Update () {

		if (!tutorialOn){
			txt.text = "";
		}
		
		
	}

	void OnTriggerStay2D(Collider2D target){
		//check that the txt object exists,
		//tutorial is on,
		//and if it's the player that's collided with it
          if( txt && tutorialOn && target.gameObject.tag == "Player")
          {
               //txt.gameObject.SetActive(true);
			   txt.text = displayText;
			   //Debug.Log("displaying text");
          }
     }
	 	void OnTriggerExit2D(Collider2D target){
			 //don't need to check if tutorial is on to remove text. 
          if(txt && target.gameObject.tag == "Player")
          {
               //txt.gameObject.SetActive(false);
			   txt.text = "";
			   //Debug.Log("removing text");
          }
     }
}
