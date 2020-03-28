using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Person: MonoBehaviour
{

    public bool hasVirus = false;
    public bool tested = false;
    public bool visibleSymptoms = false;
    public bool isDead = false;

    public bool washesHands = false;
    public bool wearsMask = false;

    public bool positiveVirus = false;

    

    public float chanceOfGoingToTest = 0;

    public PersonComponent personComponent;


    private Animator animator;
        //0 = base
    //1 = sick
    //2 = Corona
    //3 = Dead
    public int healthState = 0;
    // public enum spriteState
    // {
    //     BaseHuman,
    //     SickHuman,
    //     CoronaHuman,
    //     DeadHuman
    // }

    void Start()
    {
        animator = GetComponent<Animator>();
    }

    void Update()
    {
        updateHealthState();
        updateSprite();
    }
    public void updateHealthState(){
        if (isDead){
            healthState = 3;
        }
        else if (positiveVirus){
            healthState = 2;
        }
        else if (visibleSymptoms){
            healthState = 1;
        }
        else{
            healthState = 0;
        }
        
    }
    public void updateSprite(){
        if (healthState == 0){
            animator.SetInteger("SpriteState", 0);// SpriteState = 0;
        }
        else if (healthState == 1){
            animator.SetInteger("SpriteState", 1);
        }
        else if (healthState == 2){
            animator.SetInteger("SpriteState", 2);
        }
        else if (healthState == 3){
            animator.SetInteger("SpriteState", 3);
        }
    }


}
