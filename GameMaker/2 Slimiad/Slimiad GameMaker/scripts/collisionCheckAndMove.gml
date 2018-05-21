//collisionCheckAndMove(divide gravity by what)


if (immobile = false){

move = key_left + key_right;    //what direction. Positive, negative, or 0
hsp = move * movespeed; //how fast to move in which direction




if (vsp < 10) vsp += grav/argument0;    //decelleration when jumping

//variable jumping
if (vsp < 10) && (!forcedMove) && (!key_jump_held) vsp = max(vsp,-jumpspeed/4)
//don't ask how it works

if (place_meeting(x, y+1, obj_wall))    //forgot, but it does something
{
    vsp = key_jump * -jumpspeed
}

//horizontal collision
if (place_meeting(x+hsp, y, obj_wall))
{
    while (!place_meeting(x+sign(hsp), y, obj_wall))
    {
        x += sign(hsp); //while you're in a wall, move 1 pixel at a time out of the wall
    }
    hsp = 0;    //set speed = 0
}
//vertical collision
if (place_meeting(x, y+vsp, obj_wall))
{
    while (!place_meeting(x, y+sign(vsp), obj_wall))
    {
        y += sign(vsp); //if you're hitting the top or bottom of a wall move until you're out of it
        
    }
    vsp = 0;
    forcedMove = false;
}


x += hsp;   //actually move sprites
y += vsp;
}
