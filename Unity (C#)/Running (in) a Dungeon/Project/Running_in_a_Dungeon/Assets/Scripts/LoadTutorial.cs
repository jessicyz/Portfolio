using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class LoadTutorial : MonoBehaviour {

    public void LoadingTutorial()
    {
        SceneManager.LoadScene("intro_1", LoadSceneMode.Single);
    }
}

