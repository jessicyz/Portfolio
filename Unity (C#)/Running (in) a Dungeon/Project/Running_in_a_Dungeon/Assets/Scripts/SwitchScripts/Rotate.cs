using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Rotate : TrapComponent {



    //public bool trapOn = false;

    public float rotateSpeed = 2.0f;
    public GameObject target;
    private Vector3 z = new Vector3(0, 0, 1);



	void Start(){
		//renderer = GetComponent<SpriteRenderer>();
	}
	
	// Update is called once per frame
	void Update () {
        //trapOn = gameObject.GetComponentInParent<Switch>().trapOn;


        if (trapOn){
			//gameObject.SetActive(true);
			//transform.localScale = new Vector3(Mathf.Abs(transform.localScale.x), transform.localScale.y, 1);
			//GetComponent<Renderer>().enabled = true;
			
            this.transform.RotateAround(target.transform.position,z,rotateSpeed);
				
		}
		else{
			//gameObject.SetActive(false);
			//transform.localScale = new Vector3(Mathf.Abs(transform.localScale.x) * -1, transform.localScale.y, 1);
			//GetComponent<Renderer>().enabled = false;	
		}
	}
}
