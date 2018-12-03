using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Corpse : MonoBehaviour {

	public int amountGained = 0;
	public bool fixedAmount = false;

	// Use this for initialization

	// Rigidbody2D rb2d;
	void Start () {
		//rb2d = GetComponent<Rigidbody2D>();
		if (!fixedAmount){
			amountGained = Random.Range(10, 40);
		}
	}
	
	// Update is called once per frame
	void Update () {
		
	}



	//use layer collision matrix
	void OnTriggerEnter2D(Collider2D target)
    {
        if (target.gameObject.tag == "Player")
        {
			AudioPlayer.Instance.PlayCoinSFX();
			//Money.amount += amountGained;
			Money.AddMoney(amountGained);
			Destroy(gameObject);

			// if (Input.GetKey("z")){
				
			// }
            
        }
    }

}
