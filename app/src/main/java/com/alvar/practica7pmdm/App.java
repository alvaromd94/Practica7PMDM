package com.alvar.practica7pmdm;

import android.content.Context;

import com.alvar.practica7pmdm.Model.Lugar;

import java.util.ArrayList;
import java.util.List;

public class App {

    public final static int INSERTAR = 1;
    public final static int EDITAR = 2;
    public final static int INFORMACION = 3;

    public static int accion;

    public static Lugar lugarActivo;
    public static List<String> getListCategorias(Context context) {
        List<String> list = new ArrayList<String>();
        list.add(context.getResources().getString(R.string.bares));
        list.add(context.getResources().getString(R.string.restaurantes));
        list.add(context.getResources().getString(R.string.discotecas));
        list.add(context.getResources().getString(R.string.boleras));
        list.add(context.getResources().getString(R.string.cines));
        list.add(context.getResources().getString(R.string.todos));
        return list;
    }
}


