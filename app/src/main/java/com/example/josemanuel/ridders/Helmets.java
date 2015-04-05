package com.example.josemanuel.ridders;


import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("Helmets")
public class Helmets extends ParseObject {
    public Helmets(){

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
