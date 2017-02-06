package com.reto_final_android;

import android.graphics.drawable.Drawable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.ListView;

import java.util.ArrayList;


public class Sistema extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sistema_main);

        ArrayList<Integer> id = getIntent().getIntegerArrayListExtra("id");
        ArrayList<String> nombres = getIntent().getStringArrayListExtra("nombres");
        ArrayList<String> tipos = getIntent().getStringArrayListExtra("tipos");

        int limit = id.size();

        ArrayList<Category> category = new ArrayList<>();


        for (int i = 0; i < limit; i++) {
            Category cate = new Category();

            String item_id = id.get(i).toString();
            String item_nombre = nombres.get(i);
            String item_tipo = tipos.get(i);

            cate.setCategoryId(item_id);
            cate.setTittle(item_nombre);
            cate.setDescription(item_tipo);

            Drawable img;

            if (i == 0) {
                img = getResources().getDrawable(R.drawable.bulbasaur);
                cate.setImagen(img);
            } else if (i == 1) {
                img = getResources().getDrawable(R.drawable.ivysaur);
                cate.setImagen(img);
            } else if (i == 2) {
                img = getResources().getDrawable(R.drawable.venusaur);
                cate.setImagen(img);
            } else if (i == 3) {
                img = getResources().getDrawable(R.drawable.charmander);
                cate.setImagen(img);
            } else if (i == 4) {
                img = getResources().getDrawable(R.drawable.charmeleon);
                cate.setImagen(img);
            } else {
                img = getResources().getDrawable(R.drawable.arbol);
                cate.setImagen(img);
            }

            category.add(cate);

            ListView lv = (ListView) findViewById(R.id.listView);

            AdapterItem adapter = new AdapterItem(this, category);

            lv.setAdapter(adapter);

        }


    }
}
