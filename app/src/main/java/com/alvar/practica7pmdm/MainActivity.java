package com.alvar.practica7pmdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alvar.practica7pmdm.Logic.LogicLugar;
import com.alvar.practica7pmdm.Model.Lugar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ListView listView;

    private static List<Lugar> lstLugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.card_listView);
        listView.addHeaderView(new View(this)); // añade espacio arriba de la primera card
        listView.addFooterView(new View(this)); // añade espacio debajo de la última card

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView parent, View view, int position, long id) {
                        App.productoActivo = lstLugar.get(position - 1);
                        App.accion = App.INFORMACION;
                        startActivity(new Intent(getApplicationContext(), InformacionActivity.class));
                    }
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        CardAdapter listadoDeCards = new CardAdapter(getApplicationContext(), R.layout.list_item_card);

        lstLugar = LogicLugar.listaLugares(this);
        if (lstLugar == null) {
            Toast.makeText(this, "La base de datos está vacía.", Toast.LENGTH_LONG).show();
        } else {
            for (Lugar p : lstLugar) {
                listadoDeCards.add(p);
            }
            listView.setAdapter(listadoDeCards);
        }
    }

    public void clicNuevo(View view) {
        App.productoActivo = new Lugar();
        App.accion = App.INSERTAR;
        startActivity(new Intent(this, EdicionActivity.class));
    }

    public void clickImagen(View view) {
    }
}
