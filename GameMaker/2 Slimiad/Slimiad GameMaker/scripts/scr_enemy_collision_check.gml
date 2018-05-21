//same as player collision code, kind of


//moving
hsp = dir*movespeed;
vsp += grav;






//horizontal collision
if (place_meeting(x+hsp, y, obj_enemy_barrier))
{
    while (!place_meeting(x+sign(hsp), y, obj_enemy_barrier))
    {
        x += sign(hsp);
    }
    hsp = 0;
    dir *= -1;
}
x += hsp;



//vertical collision
if (place_meeting(x, y+vsp, obj_enemy_barrier))
{
    while (!place_meeting(x, y+sign(vsp), obj_enemy_barrier))
    {
        y += sign(vsp);
    }
    vsp = 0;
    
    if (checkLedge) && !(position_meeting(x+(sprite_width/2)*dir, y + (sprite_height/2) + checkBelow, obj_enemy_barrier)){
        dir *= -1
    }
    
    
    
}

//if (vsp < 10) vsp += grav;
y += vsp;

scr_enemy_kill();

