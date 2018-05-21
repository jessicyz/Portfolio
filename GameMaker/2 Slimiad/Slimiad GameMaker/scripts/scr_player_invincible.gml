//get inputs
getInputs();


scr_check_state();





collisionCheckAndMove(1);   //checks if it is going to collide 
                            //into something and moves
                            //

//Animate
//special case for invincibility

if (move != 0) image_xscale = move; //flip according to which side you're facing


image_speed = .5;    
    //in air?
    //scr_animate(state)
    if ( place_meeting(x, y+1, obj_wall) ){ //if you're on the ground and moving
        if (move != 0){
            //image_index = 0;
            
            sprite_index = spr_invincible_run;    //use run sprite
            
        
        }
        else{   //if you're on the ground and not moving
        
            sprite_index = spr_invincible_idle;   //use idle sprite
          
        }
    
    }
    else{   //if you're in the air. If your vertical speed is negative, you're rising and using the rising sprite. If your vertical speed is positive, you are falling and using the fall sprite
        if (vsp < 0) sprite_index = spr_invincible_jump; else sprite_index = spr_invincible_fall;
    }
    
    
    
massUse = 0;


