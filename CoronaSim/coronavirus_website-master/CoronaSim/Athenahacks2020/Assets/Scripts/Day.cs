using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using UnityEngine.SceneManagement;

using UnityEngine.UI;

public class Day: MonoBehaviour
{
    public int numAlive = 0;
    public int numSick = 0;
    public int numCorona = 0;
    public int numCoronaPositive = 0;

    
	Text txt;



	public string displayText = "";



	// Use this for initialization

	void Start () {

		txt = GetComponent<Text>();

		

	}

    void Update () {

		//displayText = amount + " G";

		txt.text = displayText;

	}

    void calculatePersonStates(){
        numAlive = CrowdController.arr_people.Count;
        numSick = 0;
        numCorona = 0;
        numCoronaPositive = 0;

        for (int i = 0; i < CrowdController.arr_people.Count; i++ ){
            if (CrowdController.arr_people[i].isDead){
                numAlive--;
            }
            else{
                if (CrowdController.arr_people[i].visibleSymptoms){
                    numSick++;
                }

                if (CrowdController.arr_people[i].hasVirus){
                    numCorona++;
                    if (CrowdController.arr_people[i].tested){
                        numCoronaPositive++;
                    }
                }
            }
        }
        
        displayText = "Number alive: " + numAlive;
        displayText += " Visibly sick: " + numSick;
        displayText += " Coronavirus Positive: " + numCoronaPositive;
        displayText += " No sympom Coronavirus: " + (numCorona - numCoronaPositive);

    }

}
