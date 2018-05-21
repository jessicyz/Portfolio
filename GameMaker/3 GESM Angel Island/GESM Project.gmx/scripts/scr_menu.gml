///scr_menu()
switch(mpos){
    case 0:
    {
        //start
        room_goto_next();
        break;
    }
    case 1:
    {
        //level select
        instance_create(x,y, obj_level_select);
        instance_destroy();
        break;
    
    }
    
    case 2: 
    {
        //quit
        game_end();
        break;
    }
    
    default: break;
}
