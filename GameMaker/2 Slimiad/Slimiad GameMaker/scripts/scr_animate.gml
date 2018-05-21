//Animate
//scr(state)

if (move != 0) image_xscale = move; //flip according to which side you're facing
//if (move != 0) image_xscale = move+(sign(move)*mass*.1);
//image_yscale = 1 + mass*.1;

    
    //in air?
    //scr_animate(state)
    if ( place_meeting(x, y+1, obj_wall) ){ //if you're on the ground and moving
        if (move != 0){
            //image_index = 0;
            image_speed = .1;
            sprite_index = asset_get_index("spr_" + argument0 + "_run");    //use run sprite
            
        
        }
        else{   //if you're on the ground and not moving
        
            sprite_index = asset_get_index("spr_" + argument0 + "_idle");   //use idle sprite
          
        }
    
    }
    else{   //if you're in the air. If your vertical speed is negative, you're rising and using the rising sprite. If your vertical speed is positive, you are falling and using the fall sprite
        if (vsp < 0) sprite_index = asset_get_index("spr_" + argument0 + "_jump"); else sprite_index = asset_get_index("spr_" + argument0 + "_fall");
    }
    
if (immobile = true){
    image_index = 0;
    image_speed = 0;

}    
    

