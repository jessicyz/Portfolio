using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class QuitGame : MonoBehaviour {

	public void QuittingGame(){
		Debug.Log("Quitting game");
		Application.Quit();
	}
}
