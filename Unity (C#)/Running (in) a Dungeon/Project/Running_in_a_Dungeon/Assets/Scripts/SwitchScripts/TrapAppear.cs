using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TrapAppear : TrapComponent {
	//bool trapOn = false;
	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		//trapOn = gameObject.GetComponentInParent<Switch>().trapOn;

		if (trapOn){
			//gameObject.SetActive(true);
			transform.localScale = new Vector3(1, 1, 1);
			GetComponent<Renderer>().enabled = true;
				
		}
		else{
			//gameObject.SetActive(false);
			transform.localScale = new Vector3(0, 0, 1);
			GetComponent<Renderer>().enabled = false;	
		}
	}
}
