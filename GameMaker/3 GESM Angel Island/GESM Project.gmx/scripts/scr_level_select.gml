///scr_menu()
switch(mpos){
    case 0:
    {
        //crowd
        room_goto(rm_crowd);
        break;
    }
    case 1:
    {
        //test
        room_goto(rm_test);
        break;
    
    }
    case 2:
    {
        //test
        room_goto(rm_barracks);
        break;
    
    }
    case 3:
    {
        //test
        room_goto(rm_mess);
        break;
    
    }
    case 4:
    {
        //test
        room_goto(rm_rec);
        break;
    
    }
    
    case 5:
    {
        //back
        instance_create(x,y, obj_menu);
        instance_destroy();
        break;
    }
    
    
    default: break;
}
