package com.example.josemanuel.ridders;

/**
 * Created by Jose Manuel on 04/03/2015.
 */
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("Categorias")
public class Categorias extends ParseObject{
    public Categorias(){

    }

    public String getCategoria(){
        return getString("categoria");
    }

    public void setCategoria(String categoria){
        put("categoria", categoria);
    }

    public ParseFile getImagen(){
        ParseFile img= (ParseFile) getParseFile("imagen");
        return img;
        //ParseFile img= (ParseFile) get("imagen");
        //String x = img.getUrl();
        //return x;
    }


}

