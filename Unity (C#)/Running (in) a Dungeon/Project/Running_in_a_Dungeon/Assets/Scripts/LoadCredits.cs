﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class LoadCredits : MonoBehaviour {

	public void LoadingCredits(){
		SceneManager.LoadScene("scene_credits", LoadSceneMode.Single);
	}
}
