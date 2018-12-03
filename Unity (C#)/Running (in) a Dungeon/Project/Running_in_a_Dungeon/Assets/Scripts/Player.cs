using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour {


    //Use Visual Studio Code instead of Visual Studio. 
    //It's better for this stuff since you don't need to compile


    //movement speed
    public float speed = 5f;

    private Rigidbody2D rb2d;


    //object to get the movement inputs
    private PlayerController controller;
    private Animator animator;




    //force to jump at
    public float jumpForce = 10.0f;
    
    //not used, but for checking ground collision
    public bool onGround = true;

    public LayerMask groundLayer;
    // a max slope of 45 degrees
    public float GroundAngleTolerance = Mathf.Cos(45.0f * Mathf.Deg2Rad);
float forceX = 0f;
        float forceY = 0f;
    void Start()
    {
        //Debug.Log("speed " + speed);
        rb2d = GetComponent<Rigidbody2D>();
        controller = GetComponent<PlayerController>();
        animator = GetComponent<Animator>();
       

    }

    // Update is called once per frame
    void FixedUpdate () {
        //+ money
        if (Input.GetKeyDown("g")){
            Money.amount += 100;
        }

        //gotta go fast
        if (Input.GetKeyDown("f")){
            speed++;
        }




        //force of movement
        //force is not the right word for X movement anymore
        forceX = 0f;
        forceY = 0f;

        
        //get current velocity
        //not being used rn
        var absVelX = Mathf.Abs(rb2d.velocity.x);
        var absVelY = Mathf.Abs(rb2d.velocity.y);

        //if the right or left arrows are pressed
        if (controller.moving.x != 0)
        {

            //forceX = speed * direction
            forceX = (speed * controller.moving.x); 
            //Debug.Log(forceX);

            //change the way you're facing
            //if moving > 0, you're facing right. Else, you're facing left
            transform.localScale = new Vector3(controller.moving.x > 0 ? -1: 1, 1, 1);

            animator.SetInteger("AnimState", 1);
            

        }
        else
        {
            animator.SetInteger("AnimState", 0);
        }


 
        
        //if you pressed up and are on the ground
        if ( controller.moving.y > 0 && onGround ) //absVelY <= 0)//onGround)
        {

            
            forceY = jumpForce * controller.moving.y;
            onGround = false;
            AudioPlayer.Instance.PlayJumpSFX();
            
        }
        if (!onGround){
            animator.SetInteger("AnimState", 2);
        }

        // else if (absVelY > 0)
        // {
        //     //animator.SetInteger("AnimState", 3);
        // }
        //add jump force (or not)
        rb2d.AddForce(new Vector2(0, forceY) , ForceMode2D.Impulse);
        // change velocity instantly (not adding force)
        rb2d.velocity = new Vector2(forceX, rb2d.velocity.y);
        //Debug.Log("vel: " + rb2d.velocity.x + " " + rb2d.velocity.y);
        //var absVelY;

        
    }



    

    // void OnCollisionEnter2D(Collision2D target)
    // {
    //     if (target.gameObject.tag == "Solid")
    //     {
    //         onGround = true;
    //     }
    // }


void OnCollisionEnter2D(Collision2D coll) 
{
    if (coll.gameObject.tag == "Solid"){
        foreach(ContactPoint2D contact in coll.contacts)
        {
            if (Vector3.Dot(contact.normal, Vector3.up) > GroundAngleTolerance)
            {
                Debug.Log("on ground");
                onGround = true;
                return;
                
            }
        }
    }
    else if (coll.gameObject.tag == "MovingPlatform"){
        

        foreach(ContactPoint2D contact in coll.contacts)
        {
            if (Vector3.Dot(contact.normal, Vector3.up) > GroundAngleTolerance)
            {
                Debug.Log("on MovingPlaform");
                onGround = true;
                return;
                
            }
        }
    }
}


// bool IsGrounded() {
//     Vector2 position = transform.position;
//     Vector2 direction = Vector2.down;
//     float distance = .5f;
    
//     RaycastHit2D hit = Physics2D.Raycast(position, direction, distance, groundLayer);
//     if (hit.collider != null) {
//         return true;
//     }
    
//     return false;
// }





}
