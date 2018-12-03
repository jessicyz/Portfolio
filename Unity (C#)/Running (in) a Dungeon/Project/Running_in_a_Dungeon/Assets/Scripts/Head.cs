using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Head : BodyPart {

	// Use this for initialization
	void Start () {
		base.Start();
		timeMultiplier = .8f;
	}
	
	// Update is called once per frame
	void Update () {
		base.Update();
	}
}
