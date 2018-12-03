using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CorpseSpawnPoint : MonoBehaviour {

	private int floorIndex = 0;
	BoxCollider2D spawnArea;

	public Corpse corpse;

	public float spawnChance = .6f;

	// Use this for initialization
	void Start () {
		


		floorIndex = gameObject.GetComponentInParent<CorpseSpawner>().floorIndex;
		spawnArea = gameObject.GetComponent<BoxCollider2D>();


		
		if ( DungeonList.arr_corpsePerFloor[floorIndex] > 0 && Random.value < spawnChance){
			SpawnCorpse();
			//Debug.Log("spawned corpse");
		}
		//Debug.Log(floorIndex);
		
		

	}
	
	// Update is called once per frame
	void Update () {
		if ( DungeonList.arr_corpsePerFloor[floorIndex] > 0){

			SpawnCorpse();
			
		}
		else{
			Destroy(gameObject);
		}
	}


	void SpawnCorpse(){

		var areaSize = spawnArea.size;
		float width = areaSize.x;
		float xPos = transform.position.x;
		float yPos = transform.position.y;
		if ( Random.value < spawnChance){
			Vector3 spawnAreaVector = new Vector3(Random.Range(xPos - width/2 , xPos + width/2) , yPos, 0);
			Corpse clone = Instantiate(corpse, spawnAreaVector, Quaternion.identity) as Corpse;
			if (clone != null) Debug.Log("spawned corpse");
			DungeonList.arr_corpsePerFloor[floorIndex]--;
		}
	}
}
