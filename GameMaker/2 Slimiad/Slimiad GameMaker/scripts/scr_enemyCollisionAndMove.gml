//scr_enemyCollisionAndMove()
//wall

if (place_meeting(x, y+1, obj_wall))
{
    //vsp = -jumpspeed;
    vsp = -1;
}




//horizontal collision
if (place_meeting(x+hsp, y, obj_wall))
{
    while (!place_meeting(x+sign(hsp), y, obj_wall))
    {
        x += sign(hsp);
    }
    hsp = -hsp;
}
//vertical collision
if (place_meeting(x, y+vsp, obj_wall))
{
    while (!place_meeting(x, y+sign(vsp), obj_wall))
    {
        y += sign(vsp);
    }
    vsp = 0;
}
x += hsp;
if (vsp < 10) vsp += grav;
y += vsp;

