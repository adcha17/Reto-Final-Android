package com.reto_final_android;

import android.content.Intent;
import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.reto_final_android.entity.PokemonEntity;
import com.reto_final_android.service.ApiImplementation;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {


    ArrayList<Integer> arrayId = new ArrayList<Integer>();
    ArrayList<String> arrayNombres = new ArrayList<String>();
    ArrayList<String> arrayTipos = new ArrayList<String>();
    ArrayList<String> arrayImagenes = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txt_id = ((EditText) findViewById(R.id.text_id)).getText().toString();
                String txt_pass = ((EditText) findViewById(R.id.text_pass)).getText().toString();

                if (txt_id.isEmpty() || txt_pass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    int id = Integer.parseInt(txt_id);
                    int limit = arrayId.size();
                    boolean isError = true;
                    for (int i = 0; i < limit; i++) {
                        if (id == (arrayId.get(i)) && txt_pass.equals(arrayTipos.get(i))) {
                            isError = false;
                            Intent new_view = new Intent(MainActivity.this, Sistema.class);
                            new_view.putIntegerArrayListExtra("id", arrayId);
                            new_view.putStringArrayListExtra("nombres", arrayNombres);
                            new_view.putStringArrayListExtra("tipos", arrayTipos);
                            new_view.putStringArrayListExtra("imagenes", arrayImagenes);

                            startActivity(new_view);
                        }
                    }
                    if (isError) {
                        Toast.makeText(getApplicationContext(), "Usuario incorrecto", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });

        ApiImplementation.getService().getPokemons(new Callback<ArrayList<PokemonEntity>>() {
            @Override
            public void success(ArrayList<PokemonEntity> lista, Response response) {


                for (PokemonEntity p : lista) {

                    arrayId.add(p.getId());
                    arrayNombres.add(p.getNombre());
                    arrayTipos.add(p.getTipo());
                    arrayImagenes.add(p.getImagen());

                }


            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i("respuesta: ", "Algo salio mal");
            }
        });


    }


}
