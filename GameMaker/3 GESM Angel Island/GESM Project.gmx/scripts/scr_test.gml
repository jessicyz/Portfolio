///scr_test()

switch(mpos){
    case 0:
    {
        //correct answer
        obj_player.daysNeeded += 0.5;
        obj_player.pause = false;
        instance_destroy();
        break;
    }
    
    case 1:
    {
        //Incorrect
        obj_player.daysNeeded += 1;
        obj_player.pause = false;
        instance_destroy();
        break;
    }
    
    case 2: 
    {
        //very incorrect
        obj_player.daysNeeded += 3;
        obj_player.pause = false;
        instance_destroy();
        break;
    }
    
    default: break;
}
