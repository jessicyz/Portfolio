if file_exists("Save.ini")
{
/*
    var LoadFile = file_text_open_read("Save.sav");
    var LoadedRoom = file_text_read_real(LoadFile);
    var LoadedSlimeMass = ini_read_real(LoadFile,"mass",1);
    file_text_close(LoadFile);
    room_goto(LoadedRoom);
*/
    ini_open("Save.ini");
    savedRoom = ini_read_real("Save","Room",1);
    room_goto( savedRoom );
    loadedPlayer = instance_create( ini_read_real("Save","x",0) , ini_read_real("Save","y",0) , obj_player)
    loadedPlayer.firepower = ini_read_real("Save","firepower",0);
    loadedPlayer.airpower = ini_read_real("Save","airpower",0);
    loadedPlayer.mass = ini_read_real("Save","mass",1);
    ini_close();
}
