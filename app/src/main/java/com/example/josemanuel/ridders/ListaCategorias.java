package com.example.josemanuel.ridders;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ListaCategorias extends Activity implements AdapterView.OnItemClickListener {

    ListView mListView;
    MAdapterCategorias mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_categorias);


        mListView = (ListView) findViewById(R.id.categorias);
        mAdapter = new MAdapterCategorias(this, new ArrayList<Backpacks>());
        mListView.setAdapter(mAdapter);

        ParseObject.registerSubclass(Backpacks.class);

        mListView.setOnItemClickListener(this);

        updateData();

    }


    public void updateData(){
        ParseQuery<Backpacks> query = ParseQuery.getQuery(Backpacks.class);
        query.findInBackground(new FindCallback<Backpacks>() {

            @Override
            public void done(List<Backpacks> tasks, ParseException error) {
                if(tasks != null){
                    mAdapter.clear();
                    mAdapter.addAll(tasks);
                }
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Backpacks task = mAdapter.getItem(position);
        TextView taskDescription = (TextView) view.findViewById(R.id.cat_description);
        ParseImageView myimage = (ParseImageView) view.findViewById(R.id.imagen);

        /*Aqui se asume que extrar el bytearray de la imagen y la pasa a la tabla pero marca errores de la imagen*/
        byte[] img = myimage.toString().getBytes();
        ParseFile upload = new ParseFile("image.jpg",img);
        upload.saveInBackground();

        /*Se crea el objeto para guardar los datos*/
        ParseObject.registerSubclass(Cart.class);
        Cart item = new Cart();
        item.setName(task.getName());
        item.setPrecio(task.getPrecio());
        item.setImage(upload);
        item.saveInBackground();

        Toast.makeText(getApplicationContext(), "Agregado al carrito", Toast.LENGTH_SHORT).show();
        task.saveEventually();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_categorias, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
