package com.example.josemanuel.ridders;


import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;

@ParseClassName("Backpacks")
public class Backpacks extends ParseObject {
    public Backpacks(){

    }

    public String getName(){
        return getString("name");
    }

    public void setName(String name){
        put("name", name);
    }

    public String getPrecio(){
        return getString("price");
    }

    public void setPrecio(String price){
        put("price", price);
    }

    public ParseFile getImage(){
        ParseFile img= (ParseFile) getParseFile("image");
        return img;
        //ParseFile img= (ParseFile) get("imagen");
        //String x = img.getUrl();
        //return x;
    }

}
