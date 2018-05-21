//if contact with the enemy, what happens








//Enemy collision
if( place_meeting(x,y,obj_player)){
    if (obj_player.y < y - sprite_height/4){ //if you're colliding with the enemy from the top
        //if (obj_player.mass > mass){
            with (obj_player){  //player does something
                vsp = -jumpspeed;
                mass += other.mass;
                //image_yscale = 2;
            }   
            instance_destroy(); //destroy instance
        //}     
        
        /*
        else{
            with (obj_player){ 
                forcedMove = true;
                vsp = -jumpspeed;
            }
        }
        */
    
    }
    
    
    
    
    else{   //if collision not at top
        //game_restart(); //start over. Temporary
        //only applies if the player is not invincible
        if (obj_player.state != states.invincible){

            with (obj_player){
                
                tempState = state;
                state = states.invincible;
                alarm[0] = invincibility_frames;
                mass -= other.decreasePlayerMass;
                
            }
        }
    
    }
}





