using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpawningShootingSpikes : TrapComponent {


    //public bool trapOn = false;
    public GameObject spikeSpawnPoint;
    public GameObject spikePrefab;
    //GameObject spawnedSpike;
    public float spawnInterval = 2.0f;
    public float spawnTimer = 0.0f;
    public float flyingSpeed = -10.0f;
    //0 for horizontal flight of the spike and 1 for vertical flight of the spike and default is horizontal 
    public int direction = 0;
    List<GameObject> spikes;
    // Use this for initialization
    void Start () {
        spikes = new List<GameObject>();
	}
	
	// Update is called once per frame
	void Update () {
        //trapOn = gameObject.GetComponentInParent<Switch>().trapOn;

        if (trapOn){
            spawnTimer = spawnTimer + Time.deltaTime;
            if(spawnTimer>=spawnInterval)
            {
                GameObject spawnedSpike = Instantiate(spikePrefab, spikeSpawnPoint.transform.position, transform.rotation);

                spawnedSpike.GetComponent<SpriteRenderer>().flipX = flyingSpeed < 0;
                
                spikes.Add(spawnedSpike);
            
                spawnTimer = 0;
            }
            //Vector3 pos = spawnedSpike.transform.position;

        }

        



        for(int i = 0;i<spikes.Count;i++)
            {
                if (spikes[i]){
                    Vector3 pos = spikes[i].transform.position;
                    if (direction == 0)
                    {
                        
                        pos.x = pos.x+flyingSpeed * Time.deltaTime;
                        spikes[i].transform.position = pos;
                    }
                    else if (direction == 1)
                    {
                        //Vector3 pos = spikes[i].transform.position;
                        pos.y =pos.y+ flyingSpeed * Time.deltaTime;
                        spikes[i].transform.position = pos;
                    }
                }
                // else{
                //     //spikes.Remove(spikes[i]);
                //     spikes.RemoveAt(i);
                // }
            }

        //spawnedSpike.transform.position = pos;
        Debug.Log(spikes.Count);
    }
}
