using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Switch : MonoBehaviour {

	SwitchText switchText;
	//TextMesh switchText_txt;

	public bool trapOn = false;

	SpriteRenderer renderer;

	public Sprite spr_off;
	public Sprite spr_on;

	public const int numTraps = 1;
	public TrapComponent[] arr_traps = new TrapComponent[numTraps];

	// Use this for initialization
	void Start () {
		//transform.localScale = new Vector3(-1, 1, 1);
		switchText = GetComponentInChildren<SwitchText>();
		renderer = GetComponent<SpriteRenderer>();
		//switchText_txt = switchText.txt;
		switchText.gameObject.SetActive(false);
		renderer.sprite = spr_off;
	}
	
	// Update is called once per frame
	void Update () {
		//switchText_txt = "";
		
		
	}

	void OnTriggerStay2D(Collider2D target)
    {

        if (target.gameObject.tag == "Player")
        {

			if (Input.GetKeyDown("z")){
				trapOn = !trapOn;
				Debug.Log("switch to " + trapOn.ToString());
				AudioPlayer.Instance.PlaySwitchSFX();
				ToggleTraps();

			}

			if (trapOn){
				//txt.text = "Trap on";

				//transform.localScale = new Vector3(1, 1, 1);
				renderer.sprite = spr_on;
				
				
			}
			else{
				//txt.text = "Trap off";
				//transform.localScale = new Vector3(-1, 1, 1); 
				//renderer.flipX = false;
				renderer.sprite = spr_off;
			}

			switchText.gameObject.SetActive(true);

            
        }
    }

	void OnTriggerExit2D(Collider2D target){
		if (target.gameObject.tag == "Player"){
			switchText.gameObject.SetActive(false);
		}
	}

	void ToggleTraps(){
		for (int i = 0; i < arr_traps.Length; i++){
			if (arr_traps[i]){
				arr_traps[i].trapOn = trapOn;
			}
		}
	}

}
