package com.alvar.practica7pmdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.alvar.practica7pmdm.Logic.LogicLugar;
import com.alvar.practica7pmdm.Model.Lugar;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ListView listView;
    Spinner spinnerCategorias;
    ImageView imageView;
    private static List<Lugar> lstLugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);

        spinnerCategorias = findViewById(R.id.spinnerCategorias);
        List<String> list = new ArrayList<String>();
        list.add(getResources().getString(R.string.todos));
        list.add(getResources().getString(R.string.bares));
        list.add(getResources().getString(R.string.restaurantes));
        list.add(getResources().getString(R.string.discotecas));
        list.add(getResources().getString(R.string.boleras));
        list.add(getResources().getString(R.string.cines));
        final int listsize = list.size();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list) {
            @Override
            public int getCount() {
                return(listsize); // Truncate the list
            }
        };
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorias.setAdapter(dataAdapter);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MapaActivity.class));
                App.lugar3 =(int) (long) spinnerCategorias.getSelectedItemId();
            }
        });

        spinnerCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerCategorias.getSelectedItem().toString()==getResources().getString(R.string.todos)){
                    mostrarTodos();
                }else{
                    mostrarUno();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listView = findViewById(R.id.card_listView);
        listView.addHeaderView(new View(this)); // añade espacio arriba de la primera card
        listView.addFooterView(new View(this)); // añade espacio debajo de la última card
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView parent, View view, int position, long id) {
                        App.lugarActivo = lstLugar.get(position - 1);
                        App.accion = App.INFORMACION;
                        startActivity(new Intent(getApplicationContext(), InformacionActivity.class));
                    }
                }
        );
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(spinnerCategorias.getSelectedItem().toString() == getResources().getString(R.string.todos))
        {
            mostrarTodos();
        }
        else
        {
            mostrarUno();
        }
    }

    public void clicNuevo(View view) {
        App.lugarActivo = new Lugar();
        App.accion = App.INSERTAR;
        App.SALIDA=2;
        startActivity(new Intent(this, EdicionActivity.class));
    }
    public void mostrarUno() {
        CardAdapter listadoDeCards = new CardAdapter(getApplicationContext(), R.layout.list_item_card);
        lstLugar = LogicLugar.listaLugares2(this, spinnerCategorias);
        if (lstLugar == null) {
            listView.setAdapter(null);
        } else {
            for (Lugar p : lstLugar) {
                listadoDeCards.add(p);
            }
            listView.setAdapter(listadoDeCards);
        }
    }
    public void mostrarTodos()
    {
        CardAdapter listadoDeCards = new CardAdapter(getApplicationContext(), R.layout.list_item_card);
        lstLugar= LogicLugar.listaLugares(this);
        if (lstLugar == null) {
            listView.setAdapter(null);
        } else {
            for (Lugar p : lstLugar) {
                listadoDeCards.add(p);
            }
            listView.setAdapter(listadoDeCards);
        }
    }
}
