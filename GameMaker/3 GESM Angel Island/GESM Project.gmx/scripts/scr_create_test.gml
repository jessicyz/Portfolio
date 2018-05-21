//scr_createTest(question,option1, option2, option3)

questions = scr_text(argument0,100, 40 - 16, window_get_height() - 110 - 16*2);




testOptions = instance_create(5, window_get_height() - 225,obj_test_options);

with (testOptions){
    //question = argument0;
    option[0] = argument1;
    option[1] = argument2;
    option[2] = argument3;
}
