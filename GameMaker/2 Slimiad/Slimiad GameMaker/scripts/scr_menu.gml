//scr_menu()
switch (mpos)
{
    case 0: 
    {
        //room_goto_next(); 
        audio_play_sound(snd_menu,1,false);
        room_goto(rm_tutorial1);
        
        break;
    }
    case 1:
    {
        audio_play_sound(snd_menu,1,false);
        scr_loadgame();
        break;
    }
    case 2:
    {
        audio_play_sound(snd_menu,1,false);
        room_goto(rm_credits);
        break;
    }
    case 3: game_end(); break;
    
    default: break;

}
