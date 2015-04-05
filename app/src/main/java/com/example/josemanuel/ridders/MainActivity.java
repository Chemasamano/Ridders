package com.example.josemanuel.ridders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;




public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    ListView mListView;
    MAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button carrito = (Button) findViewById(R.id.verCarrito);
        carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ListarCarrito.class);
                startActivity(intent);
            }
        });

        mListView = (ListView) findViewById(R.id.categorias_list);
        mAdapter = new MAdapter(this, new ArrayList<Categorias>());
        mListView.setAdapter(mAdapter);

        ParseObject.registerSubclass(Categorias.class);

        mListView.setOnItemClickListener(this);

        updateData();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            ParseUser.getCurrentUser().logOut();
            startActivity(new Intent(MainActivity.this,Dispatch.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateData(){
        ParseQuery<Categorias> query = ParseQuery.getQuery(Categorias.class);
        query.findInBackground(new FindCallback<Categorias>() {

            @Override
            public void done(List<Categorias> tasks, ParseException error) {
                if(tasks != null){
                    mAdapter.clear();
                    mAdapter.addAll(tasks);
                }
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Categorias task = mAdapter.getItem(position);
        TextView taskDescription = (TextView) view.findViewById(R.id.cat_description);

        /*task.setCompleted(!task.isCompleted());

        if(task.isCompleted()){
            taskDescription.setPaintFlags(taskDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            taskDescription.setPaintFlags(taskDescription.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }*/

        Log.d("Item Clicked", task.getCategoria());

        switch (task.getCategoria()) {

            case "BACKPACKS":
                Intent intent  = new Intent(getApplicationContext(),ListaCategorias.class);
                startActivity(intent);
                break;
            case "BOOTS":
                Intent intent_boots  = new Intent(getApplicationContext(),ListaCategoriasBoots.class);
                startActivity(intent_boots);
                break;
            case "GLOVES":
                Intent intent_gloves  = new Intent(getApplicationContext(),ListaCategoriasGloves.class);
                startActivity(intent_gloves);
                break;
            case "HELMETS":
                Intent intent_helmets  = new Intent(getApplicationContext(),ListaCategoriasHelmets.class);
                startActivity(intent_helmets);
                break;
            case "JACKETS":
                Intent intent_jackets  = new Intent(getApplicationContext(),ListaCategoriasJackets.class);
                startActivity(intent_jackets);
                break;
            case "PANTS":
                Intent intent_pants  = new Intent(getApplicationContext(),ListaCategoriasPants.class);
                startActivity(intent_pants);
                break;
            case "PROTECTION":
                Intent intent_protection  = new Intent(getApplicationContext(),ListaCactegoriasProtection.class);
                startActivity(intent_protection);
                break;
            default:
                Toast.makeText(getApplicationContext(),"No valido", Toast.LENGTH_SHORT).show();
                break;
        }


        task.saveEventually();


    }


}

