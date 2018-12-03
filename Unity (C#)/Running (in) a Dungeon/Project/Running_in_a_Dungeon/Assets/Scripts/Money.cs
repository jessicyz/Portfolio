using System.Collections;

using System.Collections.Generic;

using UnityEngine;

using UnityEngine.SceneManagement;

using UnityEngine.UI;

public class Money : MonoBehaviour {



	public static int defaultAmount = 15;

	public static int amount = defaultAmount;

	static int fixedDeduction = 5;




	Text txt;



	public string displayText = "";



	// Use this for initialization

	void Start () {

		txt = GetComponent<Text>();

		

	}

	

	// Update is called once per frame

	void Update () {

		displayText = amount + " G";

		txt.text = displayText;

	}



	public static int DeathToll(){

		int tenPercent = (int)(amount * 0.1f + 0.5f);

		int amountDeducted = 0;

		if (tenPercent > fixedDeduction){

			//amount -= (int)(amount * 0.1f + 0.5f);

			amountDeducted = tenPercent;

		}

		else{

			//amount -= fixedDeduction;

			amountDeducted = fixedDeduction;

		}

		amount -= amountDeducted;

		CheckIfOutOfMoney();



		return amountDeducted;

		



	}



	public static void RestartLevelToll(){

		amount -= fixedDeduction;

		CheckIfOutOfMoney();

	}



	public static void CheckIfOutOfMoney(){

		if (amount < 0){

			SceneManager.LoadScene(DungeonList.dungeon_gameOver.floorName);

			amount = defaultAmount;

			DungeonList.ResetLevels();
            UniversalUpgradeScript.reset = true;

		}



		

	}



	public static void DeductMoney(int deduction){

		amount -= deduction;

		CheckIfOutOfMoney();

	}



	public static void AddMoney(int add){

		amount += add;

	}







}

