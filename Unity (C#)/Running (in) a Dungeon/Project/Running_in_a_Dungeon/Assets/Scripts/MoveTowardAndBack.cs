using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MoveTowardAndBack : MonoBehaviour {

    bool forward = true;
    public GameObject target;
    public float speed = 10.0f;
    Vector3 pos;
    Vector3 targetPos;

    public bool slippery = false;
	// Use this for initialization
	void Start () {
        pos = transform.position;
        targetPos = target.transform.position;
		
	}
	
	// Update is called once per frame
	void Update () {
        if(transform.position == targetPos)
        {
            forward = false;
        }
        else if(transform.position == pos)
        {
            forward = true;
        }
        else
        {
            //nothing
        }

        float step = speed * Time.deltaTime;
        if (forward)
        {
            transform.position = Vector3.MoveTowards(transform.position, targetPos, step);
        }
        else
        {
            transform.position = Vector3.MoveTowards(transform.position, pos, step);
        }
        
        

    }

    void OnCollisionStay2D(Collision2D coll) {
        if (!slippery){
            GameObject collidedObject = coll.gameObject;
            if (collidedObject.tag == "Player" || collidedObject.tag == "Corpse" ){
                collidedObject.transform.SetParent(transform);
                //collidedObject.transform.localScale = new Vector3(1, 1, 1);
            }
        }

    }
    void OnCollisionExit2D(Collision2D coll){
        if (!slippery){
            GameObject collidedObject = coll.gameObject;
            if (collidedObject.tag == "Player" || collidedObject.tag == "Corpse" ){
                collidedObject.transform.parent = null;
            }
        }
    }


}
