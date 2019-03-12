package com.alvar.practica7pmdm;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alvar.practica7pmdm.DataBaseManager.DB_SQLite;
import com.alvar.practica7pmdm.DataBaseManager.Esquema;

public class InformacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        TextView txtNombre1 = findViewById(R.id.txtNombre1);
        TextView txtLatitud1 = findViewById(R.id.txtLatitud1);
        TextView txtLongitud1 = findViewById(R.id.txtLongitud1);
        TextView txtCategoria1 = findViewById(R.id.txtCategoria1);
        TextView txtComentarios1 = findViewById(R.id.txtComentarios1);
        RatingBar rbInformacion = findViewById(R.id.rbInformacion);
    if(App.lugarActivo==null)
    {

    }
    else
    {
        txtNombre1.setText(App.lugarActivo.getNombre());
        txtLatitud1.setText(App.lugarActivo.getLatitud().toString());
        txtLongitud1.setText(App.lugarActivo.getLongitud().toString());
        txtCategoria1.setText(App.getListCategorias(this).get(App.lugarActivo.getCategoria() - 1));
        txtComentarios1.setText(App.lugarActivo.getComentarios());
        rbInformacion.setRating(App.lugarActivo.getValoracion());
    }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_informacion, menu);
        return true;
    }

    @SuppressLint("NewApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opcionEditar:
                App.SALIDA = 1;
                startActivity(new Intent(getApplicationContext(), EdicionActivity.class));
                finish();
                break;
            case R.id.opcionBorrar:
                confirmacionEliminar();

                break;
        }
        return false;
    }

    private void eliminar()
    {
        DB_SQLite db = new DB_SQLite(this);
        SQLiteDatabase conn = db.getWritableDatabase();
        String sqlWhere = Esquema.Lugar.COLUMN_NAME_ID + " LIKE '" + App.lugarActivo.getId() + "'";
        conn.delete(Esquema.Lugar.TABLE_NAME, sqlWhere, null);
        Toast.makeText(this, getResources().getString(R.string.toastEliminado), Toast.LENGTH_LONG).show();
        conn.close();
    }
    private void confirmacionEliminar()
    {
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.eliminarInformacion))
                .setMessage(getResources().getString(R.string.confirmareliminar))
                .setPositiveButton(getResources().getString(R.string.eliminar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        eliminar();
                        finish();
                    }
                })
                .setNegativeButton(getResources().getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}