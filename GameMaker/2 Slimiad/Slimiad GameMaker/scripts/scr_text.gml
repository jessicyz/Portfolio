//scr_text("Text",speed,x,y);

txt = instance_create(view_xview[0]+20,view_yview[0]+20,obj_text);
with (txt)
{
    padding = 16;
    maxlength = view_wview[0] - 50;
    text = argument0;
    spd = argument1;
    font = fnt_textbox;
    
    text_length = string_length(text);
    font_size = font_get_size(font);
    
    draw_set_font(font);
    
    text_width = string_width_ext(text,font_size+(font_size/2), maxlength);
    text_height = string_height_ext(text,font_size+(font_size/2), maxlength);
    
    boxwidth = text_width + (padding*2);
    boxheight = text_height + (padding*2);
}
