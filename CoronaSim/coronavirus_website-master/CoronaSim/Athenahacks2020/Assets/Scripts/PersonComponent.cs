using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PersonComponent : MonoBehaviour
{
    private Animator animator;
        //0 = base
    //1 = sick
    //2 = Corona
    //3 = Dead
    public int healthState = 3;
    // public enum spriteState
    // {
    //     BaseHuman,
    //     SickHuman,
    //     CoronaHuman,
    //     DeadHuman
    // }
    // Start is called before the first frame update
    void Start()
    {
        animator = GetComponent<Animator>();
    }

    // Update is called once per frame
    void Update()
    {
        
    }
    
    void updateSprite(){
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
