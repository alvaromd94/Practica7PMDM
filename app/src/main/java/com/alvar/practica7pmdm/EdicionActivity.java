package com.alvar.practica7pmdm;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import com.alvar.practica7pmdm.Logic.LogicLugar;


public class EdicionActivity extends AppCompatActivity implements LocationListener {

    EditText txtNombre, txtLatitud, txtLongitud, txtComentarios;
    RatingBar rbEdicion;
    Spinner spinnerCategoria;
    ImageView imageViewLocalizacion;
    final String TAG = "GPS";

    private final static int ALL_PERMISSIONS_RESULT = 101;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
    LocationManager locationManager;
    Location loc;

    ArrayList permissions = new ArrayList<>();
    ArrayList<String> permissionsToRequest;
    ArrayList<String> permissionsRejected = new ArrayList<>();

    boolean isGPS = false;
    boolean isNetwork = false;
    boolean canGetLocation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion);

        txtNombre= findViewById(R.id.txtNombre);
        txtLatitud= findViewById(R.id.txtLatitud);
        txtLongitud= findViewById(R.id.txtLongitud);
        txtComentarios= findViewById(R.id.txtComentarios);
        spinnerCategoria= findViewById(R.id.spinnerCategoria);
        rbEdicion= findViewById(R.id.rbEdicion);
        imageViewLocalizacion= findViewById(R.id.imageViewLocalizacion);
        List<String> list = App.getListCategorias(this);
        final int listsize = list.size();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list) {
            @Override
            public int getCount() {
                return(listsize);
            }
        };
        imageViewLocalizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isGPS && !isNetwork) {
                    Log.i(TAG, getResources().getString(R.string.conexionoff));
                    showSettingsAlert();
                    getLastLocation();
                } else {
                    Log.i(TAG, getResources().getString(R.string.conexionon));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (permissionsToRequest.size() > 0) {
                            requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
                            canGetLocation = false;
                        }
                    }
                    getLocation();
                }
            }
        });
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adaptador);

        if(App.SALIDA==1)
        {
            txtNombre.setText(App.lugarActivo.getNombre());
            spinnerCategoria.setSelection(App.lugarActivo.getCategoria()-1);
            txtLatitud.setText(App.lugarActivo.getLongitud().toString());
            txtLongitud.setText(App.lugarActivo.getLatitud().toString());
            rbEdicion.setRating(App.lugarActivo.getValoracion());
            txtComentarios.setText(App.lugarActivo.getComentarios());
        }
        else if(App.SALIDA==2)
        {

        }

        locationManager = (LocationManager) getSystemService(Service.LOCATION_SERVICE);
        isGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionsToRequest = findUnAskedPermissions(permissions);
    }
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edicion, menu);
        MenuBuilder m = (MenuBuilder) menu;
        m.setOptionalIconsVisible(true);
        return true;
    }
    @SuppressLint("NewApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(!txtNombre.getText().toString().isEmpty() && !txtLatitud.getText().toString().isEmpty() && !txtLongitud.toString().isEmpty() && !txtComentarios.toString().isEmpty())
        {
            if(App.SALIDA==1)
            {
                App.lugarActivo.setNombre(txtNombre.getText().toString());
                App.lugarActivo.setLatitud(Float.parseFloat(txtLongitud.getText().toString()));
                App.lugarActivo.setLongitud(Float.parseFloat(txtLatitud.getText().toString()));
                App.lugarActivo.setComentarios(txtComentarios.getText().toString());
                App.lugarActivo.setValoracion(rbEdicion.getRating());
                App.lugarActivo.setCategoria(spinnerCategoria.getSelectedItemPosition()+1);
                LogicLugar.editarLugar(this, App.lugarActivo );
                Toast.makeText(this, getResources().getString(R.string.toastEditado), Toast.LENGTH_LONG).show();
                finish();
            }
            else
            {
                App.lugarActivo.setNombre(txtNombre.getText().toString());
                App.lugarActivo.setLatitud(Float.parseFloat(txtLongitud.getText().toString()));
                App.lugarActivo.setLongitud(Float.parseFloat(txtLatitud.getText().toString()));
                App.lugarActivo.setComentarios(txtComentarios.getText().toString());
                App.lugarActivo.setValoracion(rbEdicion.getRating());
                App.lugarActivo.setCategoria(spinnerCategoria.getSelectedItemPosition()+1);
                LogicLugar.insertarLugar(this, App.lugarActivo );
                Toast.makeText(this, getResources().getString(R.string.toastCreado), Toast.LENGTH_LONG).show();
                finish();
            }
        }
        else
        {

        }
        return false;
    }
    @Override
    public void onLocationChanged(Location location) {
        updateUI(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
        getLocation();
    }

    @Override
    public void onProviderDisabled(String s) {
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }
    private void getLocation() {
        try {
            if (canGetLocation) {
                if (isGPS) { // recibiendo señal desde GPS_PROVIDER
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if (locationManager != null) {
                        loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (loc != null)
                            updateUI(loc);
                    }
                } else if (isNetwork) { // recibiendo señal desde NETWORK_PROVIDER
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if (locationManager != null) {
                        loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (loc != null)
                            updateUI(loc);
                    }
                } else {
                    loc.setLatitude(0);
                    loc.setLongitude(0);
                    updateUI(loc);
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
    private void getLastLocation() {
        try {
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, false);
            Location location = locationManager.getLastKnownLocation(provider);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private ArrayList findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList result = new ArrayList();
        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }
        return result;
    }

    private boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
        }
        return true;
    }
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case ALL_PERMISSIONS_RESULT:
                for (String perms : permissionsToRequest) {
                    if (!hasPermission(perms)) {
                        permissionsRejected.add(perms);
                    }
                }
                if (permissionsRejected.size() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            showMessageOKCancel(getResources().getString(R.string.permisos),
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                        }
                                    });
                            return;
                        }
                    }
                } else
                    {
                    canGetLocation = true;
                    getLocation();
                }
                break;
        }
    }
    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(getResources().getString(R.string.gpsNo));
        alertDialog.setMessage(getResources().getString(R.string.activarGPS));
        alertDialog.setPositiveButton(getResources().getString(R.string.si), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });
        alertDialog.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(getResources().getString(R.string.ok), okListener)
                .setNegativeButton(getResources().getString(R.string.cancelar), null)
                .create()
                .show();
    }

    private void updateUI(Location loc) {
        txtLatitud.setText(Double.toString(loc.getLatitude()));
        txtLongitud.setText(Double.toString(loc.getLongitude()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }
}

