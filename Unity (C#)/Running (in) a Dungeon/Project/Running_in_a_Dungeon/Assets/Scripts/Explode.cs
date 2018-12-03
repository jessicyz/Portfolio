using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Explode : MonoBehaviour {
    private bool cameraOn = false;
    public Camera camera;
    public BodyPart bodyPart;
    public Head head;

    public BodyPart moneyParticle;
    public int totalParts = 20;

    public bool canExplode = true;
    public RespawnPoint respawnPoint;

	// Use this for initialization
	void Start () {
		 if (camera.gameObject.active){
            cameraOn = true;
        }
	}
	
	// Update is called once per frame
	void Update () {
		canExplode = true;


        if (Input.GetKeyDown("r") ){
            Money.RestartLevelToll();
            Respawn();
        }
	}

    void OnTriggerEnter2D(Collider2D target)
    {
        if (target.gameObject.tag == "Deadly")
        {
            CheckCanDie();
        }
        
    }

    void OnCollisionEnter2D(Collision2D target)
    {
        if (target.gameObject.tag == "Deadly")
        {
            CheckCanDie();
        }
    }

    public void OnExplode()
    {
        //Destroy(gameObject);
        if (cameraOn){
            camera.gameObject.transform.parent = null;
        }


        Debug.Log("boom");
        AudioPlayer.Instance.PlayDeathSFX();
        int amountDeducted = Money.DeathToll();
        //if (Money.amount < 0) Money.amount = 0;
        var t = transform;


        //head body part
        {
            Head headClone = Instantiate(head, t.position, Quaternion.identity) as Head;
            Rigidbody2D rb2d = headClone.GetComponent<Rigidbody2D>();
        
            rb2d.GetComponent<Rigidbody2D>().AddForce(Vector3.up * Random.Range(300, 500));
            rb2d.AddTorque( Random.Range(-300, 300) );
        }


        //regular body parts
        for (int i = 0; i < totalParts; i++){
            t.TransformPoint(0, -100, 0);
            
            BodyPart clone = Instantiate(bodyPart, t.position, Quaternion.identity) as BodyPart;
            AddSpin(clone);
        }


        //money explosion
        if (amountDeducted > 150){
            amountDeducted = 150;
        }
        for (int i = 0; i < amountDeducted; i++){
            t.TransformPoint(0, -100, 0);
            BodyPart moneyClone = Instantiate(moneyParticle, t.position, Quaternion.identity) as BodyPart;
            Rigidbody2D rb2d = moneyClone.GetComponent<Rigidbody2D>();
            rb2d.AddForce(Vector3.right * Random.Range(-50, 50));
            rb2d.AddForce(Vector3.up * Random.Range(100, 200));
            rb2d.AddTorque( Random.Range(-300, 300) );
        }

        Respawn();
        //transform.position = respawnPoint.transform.position;
        //isExploding = false;
    }

    public void AddSpin(BodyPart clone){
        Rigidbody2D rb2d = clone.GetComponent<Rigidbody2D>();
        rb2d.AddForce(Vector3.right * Random.Range(-50, 50));
        rb2d.AddForce(Vector3.up * Random.Range(100, 400));
        rb2d.AddTorque( Random.Range(-300, 300) );
    }

    public void CheckCanDie(){
        if (canExplode){
            canExplode = false;
            
            Debug.Log("boom");
            OnExplode();
            
        }
    }

    public void Respawn(){
        transform.position = respawnPoint.transform.position;
    }
}
