package com.example.josemanuel.ridders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseImageView;

import java.util.List;


/**
 * Created by Jose Manuel on 04/03/2015.
 */
public class MAdapterCategoriasCarrito extends ArrayAdapter<Cart> {
    private Context mContext;
    private List<Cart> mTasks;
    //ImageLoader imageLoader;

    //ListView mListView;
    //TaskAdapter mAdapter;
    //mListView.setOnItemClickListener(this);

    public MAdapterCategoriasCarrito(Context context, List<Cart> objects) {
        super(context, R.layout.row_item_categoria, objects);
        this.mContext = context;
        this.mTasks = objects;

        // mListView.setOnItemClickListener(this);
    }


    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.row_item_categoria, null);
        }

        Cart task = mTasks.get(position);


        //ParseFile img = (ParseFile)Categorias.get


        TextView descriptionView = (TextView) convertView.findViewById(R.id.cat_description);
        TextView precioView = (TextView) convertView.findViewById(R.id.cat_precio);
        ParseImageView imgView= (ParseImageView) convertView.findViewById(R.id.imagen);

        //ImageView imgView = (ImageView) convertView.findViewById(R.id.imagen);

        descriptionView.setText(task.getName());
        precioView.setText(task.getPrecio());
        imgView.setParseFile(task.getImage());

        imgView.loadInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, ParseException e) {

            }
        });

        /*if(task.isCompleted()){
            descriptionView.setPaintFlags(descriptionView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            descriptionView.setPaintFlags(descriptionView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }*/

        return convertView;
    }

}
