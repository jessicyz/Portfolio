//switch states
if(keyboard_check_pressed(ord("1")) ){

    state = states.normal;
}

if(keyboard_check_pressed(ord("2")) && firepower = true){

    state = states.fire;
}
if(keyboard_check_pressed(ord("3")) ){  //&& airpower = true

    state = states.air;
}
if(keyboard_check_pressed(ord("0"))){
    state = states.invincible;
}
