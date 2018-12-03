using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class SwitchText : MonoBehaviour {

	public TextMesh txt;

	bool trapOn = false;
	// Use this for initialization
	
	void Start () {
		txt = GetComponent<TextMesh>();

	}
	
	// Update is called once per frame
	void Update () {
		trapOn = gameObject.GetComponentInParent<Switch>().trapOn;


		if (trapOn){
			//transform.localScale = new Vector3(Mathf.Abs(transform.localScale.x), transform.localScale.y, 1); 
			txt.text = "Trap on";
				
		}
		else{
			//temporarily reverse the reverse text. To be replaced this has an actual asset
			//transform.localScale = new Vector3(Mathf.Abs(transform.localScale.x) * -1, transform.localScale.y, 1);
			txt.text = "Trap off";
				
		}
	
	}
}
