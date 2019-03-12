package com.alvar.practica7pmdm.Logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Spinner;
import com.alvar.practica7pmdm.DataBaseManager.DB_SQLite;
import com.alvar.practica7pmdm.DataBaseManager.Esquema;
import com.alvar.practica7pmdm.Model.Lugar;
import java.util.ArrayList;
import java.util.List;

public class LogicLugar {

    public static void insertarLugar(Context context, Lugar p) {
        ContentValues content = new ContentValues();
        content.put(Esquema.Lugar.COLUMN_NAME_NOMBRE, p.getNombre());
        content.put(Esquema.Lugar.COLUMN_NAME_LATITUD, p.getLatitud());
        content.put(Esquema.Lugar.COLUMN_NAME_LONGITUD, p.getLongitud());
        content.put(Esquema.Lugar.COLUMN_NAME_COMENTARIOS, p.getComentarios());
        content.put(Esquema.Lugar.COLUMN_NAME_VALORACION, p.getValoracion());
        content.put(Esquema.Lugar.COLUMN_NAME_CATEGORIA, p.getCategoria());
        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_WRITE);
        conn.insert(Esquema.Lugar.TABLE_NAME, null, content);
        DB_SQLite.desconectar(conn);
    }

    public static void eliminarLugar(Context context, Lugar p) {
        String sqlWhere = Esquema.Lugar.COLUMN_NAME_ID + " = " + p.getId();
        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_WRITE);
        conn.delete(Esquema.Lugar.TABLE_NAME, sqlWhere, null);
        DB_SQLite.desconectar(conn);
    }

    public static void editarLugar(Context context, Lugar p) {
        ContentValues content = new ContentValues();
        content.put(Esquema.Lugar.COLUMN_NAME_NOMBRE, p.getNombre());
        content.put(Esquema.Lugar.COLUMN_NAME_LATITUD, p.getLatitud());
        content.put(Esquema.Lugar.COLUMN_NAME_LONGITUD, p.getLongitud());
        content.put(Esquema.Lugar.COLUMN_NAME_COMENTARIOS, p.getComentarios());
        content.put(Esquema.Lugar.COLUMN_NAME_VALORACION, p.getValoracion());
        content.put(Esquema.Lugar.COLUMN_NAME_CATEGORIA, p.getCategoria());
        String sqlWhere = Esquema.Lugar.COLUMN_NAME_ID + " = " + p.getId();
        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_WRITE);
        conn.update(Esquema.Lugar.TABLE_NAME, content, sqlWhere, null);
        DB_SQLite.desconectar(conn);
    }

    public static List listaLugares(Context context) {
        List lug = new ArrayList<>();
        String[] sqlFields = {Esquema.Lugar.COLUMN_NAME_ID, Esquema.Lugar.COLUMN_NAME_LATITUD, Esquema.Lugar.COLUMN_NAME_LONGITUD, Esquema.Lugar.COLUMN_NAME_NOMBRE, Esquema.Lugar.COLUMN_NAME_COMENTARIOS, Esquema.Lugar.COLUMN_NAME_VALORACION, Esquema.Lugar.COLUMN_NAME_CATEGORIA};
        String sqlWhere = "";
        String sqlOrderBy = Esquema.Lugar.COLUMN_NAME_NOMBRE + " ASC";

        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_READ);
        Cursor cursor = conn.query(Esquema.Lugar.TABLE_NAME, sqlFields, sqlWhere, null, null, null, sqlOrderBy);
        if (cursor.getCount() == 0) {
            lug = null;
        } else {
            cursor.moveToFirst();
            do {
                Long dataId = cursor.getLong(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_ID));
                String dataNombre = cursor.getString(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_NOMBRE));
                Double dataLatitud = cursor.getDouble(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_LATITUD));
                Double dataLongitud = cursor.getDouble(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_LONGITUD));
                String dataComentarios = cursor.getString(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_COMENTARIOS));
                Float dataValoracion = cursor.getFloat(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_VALORACION));
                Integer dataCategoria = cursor.getInt(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_CATEGORIA));
                lug.add(new Lugar(dataId, dataNombre, dataLatitud, dataLongitud, dataComentarios, dataValoracion, dataCategoria));
            } while (cursor.moveToNext());
        }
        cursor.close();
        DB_SQLite.desconectar(conn);
        return lug;
    }

    public static List listaLugares2(Context context, Spinner spinner) {
        List lug = new ArrayList<>();
        String[] sqlFields = {Esquema.Lugar.COLUMN_NAME_ID, Esquema.Lugar.COLUMN_NAME_NOMBRE, Esquema.Lugar.COLUMN_NAME_LATITUD, Esquema.Lugar.COLUMN_NAME_LONGITUD, Esquema.Lugar.COLUMN_NAME_COMENTARIOS, Esquema.Lugar.COLUMN_NAME_VALORACION, Esquema.Lugar.COLUMN_NAME_CATEGORIA};
        String sqlWhere = "categoria=" + spinner.getSelectedItemPosition();
        String sqlOrderBy = Esquema.Lugar.COLUMN_NAME_NOMBRE + " ASC";

        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_READ);
        Cursor cursor = conn.query(Esquema.Lugar.TABLE_NAME, sqlFields, sqlWhere, null, null, null, sqlOrderBy);
        if (cursor.getCount() == 0) {
            lug = null;
        } else {
            cursor.moveToFirst();
            do {
                Long dataId = cursor.getLong(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_ID));
                String dataNombre = cursor.getString(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_NOMBRE));
                Double dataLatitud = cursor.getDouble(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_LATITUD));
                Double dataLongitud = cursor.getDouble(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_LONGITUD));
                String dataComentarios = cursor.getString(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_COMENTARIOS));
                Float dataValoracion = cursor.getFloat(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_VALORACION));
                int dataCategoria = cursor.getInt(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_CATEGORIA));
                lug.add(new Lugar(dataId, dataNombre, dataLatitud, dataLongitud, dataComentarios, dataValoracion, dataCategoria));
            } while (cursor.moveToNext());
        }
        cursor.close();
        DB_SQLite.desconectar(conn);
        return lug;
    }

    public static List listaLugares3(Context context, int Spinner) {
        List lug = new ArrayList<>();
        String[] sqlFields = {Esquema.Lugar.COLUMN_NAME_ID, Esquema.Lugar.COLUMN_NAME_NOMBRE, Esquema.Lugar.COLUMN_NAME_LATITUD, Esquema.Lugar.COLUMN_NAME_LONGITUD, Esquema.Lugar.COLUMN_NAME_COMENTARIOS, Esquema.Lugar.COLUMN_NAME_VALORACION, Esquema.Lugar.COLUMN_NAME_CATEGORIA};
        String sqlWhere = "categoria=" + Spinner;
        String sqlOrderBy = Esquema.Lugar.COLUMN_NAME_NOMBRE + " ASC";

        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_READ);
        Cursor cursor = conn.query(Esquema.Lugar.TABLE_NAME, sqlFields, sqlWhere, null, null, null, sqlOrderBy);
        if (cursor.getCount() == 0) {
            lug = null;
        } else {
            cursor.moveToFirst();
            do {
                Long dataId = cursor.getLong(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_ID));
                String dataNombre = cursor.getString(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_NOMBRE));
                Double dataLatitud = cursor.getDouble(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_LATITUD));
                Double dataLongitud = cursor.getDouble(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_LONGITUD));
                String dataComentarios = cursor.getString(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_COMENTARIOS));
                Float dataValoracion = cursor.getFloat(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_VALORACION));
                int dataCategoria = cursor.getInt(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_CATEGORIA));
                lug.add(new Lugar(dataId, dataNombre, dataLatitud, dataLongitud, dataComentarios, dataValoracion, dataCategoria));
            } while (cursor.moveToNext());
        }
        cursor.close();
        DB_SQLite.desconectar(conn);
        return lug;
    }

    public static Lugar listaLugares4(Context context, String latitud, String longitud) {
        Lugar lug = null;
        String[] sqlFields = {Esquema.Lugar.COLUMN_NAME_ID, Esquema.Lugar.COLUMN_NAME_NOMBRE, Esquema.Lugar.COLUMN_NAME_LATITUD, Esquema.Lugar.COLUMN_NAME_LONGITUD, Esquema.Lugar.COLUMN_NAME_COMENTARIOS, Esquema.Lugar.COLUMN_NAME_VALORACION, Esquema.Lugar.COLUMN_NAME_CATEGORIA};
        String sqlWhere = "latitud='" + latitud + "'and longitud='" + longitud + "'";
        String sqlOrderBy = Esquema.Lugar.COLUMN_NAME_NOMBRE + " ASC";

        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_READ);
        Cursor cursor = conn.query(Esquema.Lugar.TABLE_NAME, sqlFields, sqlWhere, null, null, null, sqlOrderBy);
        if (cursor.getCount() == 0) {
            lug = null;
        } else {
            cursor.moveToFirst();
            Long dataId = cursor.getLong(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_ID));
            String dataNombre = cursor.getString(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_NOMBRE));
            Double dataLatitud = cursor.getDouble(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_LATITUD));
            Double dataLongitud = cursor.getDouble(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_LONGITUD));
            String dataComentarios = cursor.getString(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_COMENTARIOS));
            Float dataValoracion = cursor.getFloat(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_VALORACION));
            int dataCategoria = cursor.getInt(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_CATEGORIA));
            lug = new Lugar(dataId, dataNombre, dataLatitud, dataLongitud, dataComentarios, dataValoracion, dataCategoria);


            cursor.close();
            DB_SQLite.desconectar(conn);
        }
        return lug;

    }
}
