using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class StartGame : MonoBehaviour
{
    // Start is called before the first frame update
    public void StartingGame()
    {
        SceneManager.LoadScene("scene_main", LoadSceneMode.Single);
    }
}
