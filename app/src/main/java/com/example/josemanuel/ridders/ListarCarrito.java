package com.example.josemanuel.ridders;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class ListarCarrito extends Activity implements AdapterView.OnItemClickListener {

    ListView mListView;
    MAdapterCategoriasCarrito mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_carrito);
        mListView = (ListView) findViewById(R.id.categorias_carrito);
        mAdapter = new MAdapterCategoriasCarrito(this, new ArrayList<Cart>());
        mListView.setAdapter(mAdapter);

        ParseObject.registerSubclass(Cart.class);

        mListView.setOnItemClickListener(this);

        updateData();
    }

    public void updateData(){
        ParseQuery<Cart> query = ParseQuery.getQuery(Cart.class);
        query.findInBackground(new FindCallback<Cart>() {

            @Override
            public void done(List<Cart> tasks, ParseException error) {
                if(tasks != null){
                    mAdapter.clear();
                    mAdapter.addAll(tasks);
                }
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cart task = mAdapter.getItem(position);
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

        task.saveEventually();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listar_carrito, menu);
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
