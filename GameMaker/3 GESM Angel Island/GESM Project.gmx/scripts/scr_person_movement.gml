///Person movement
///scr_menu()
switch( random_range(0, 7) ){
    case 0:
    {
        //stand still
        moveHor = 0;
        moveVer = 0;
        break;
    }
    
    case 1:
    {
        //move right up
        moveHor = 1;
        moveVer = -1;
        break;
    }
    
    case 2: 
    {
        //move right
        moveHor = 1;
        moveVer = 0;
        break;
    }
    
    case 3: 
    {
        //move right down
        moveHor = 1;
        moveVer = 1;
        break;
    }
    case 4: 
    {
        //move down
        moveHor = 0;
        moveVer = 1;
        break;
    }
    
    case 5: 
    {
        //move left down
        moveHor = -1;
        moveVer = 1;
        break;
    }
    
    case 6: 
    {
        //move left 
        moveHor = -1;
        moveVer = 0;
        break;
    }
    
    case 7: 
    {
        //move left up
        moveHor = -1;
        moveVer = -1;
        break;
    }
    
    default: break;
}
