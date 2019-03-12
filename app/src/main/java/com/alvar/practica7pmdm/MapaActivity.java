package com.alvar.practica7pmdm;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.alvar.practica7pmdm.Logic.LogicLugar;
import com.alvar.practica7pmdm.Model.Lugar;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import static com.alvar.practica7pmdm.App.lugar3;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    LatLng nuevaPosicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mostrarTodos();
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomTo(5), 2000, null);
        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        if (marker.equals(marker))
        {

            String cadena = marker.getSnippet();
            String cadena1[] = cadena.split(",");
            String latitud  = cadena1[0];
            String longitud = cadena1 [1];
            Lugar p = LogicLugar.listaLugares4(this, latitud, longitud);
            App.lugarActivo=p;

            Intent intent=new Intent(this,InformacionActivity.class);
            startActivity(intent);


        }

        return false;
    }

    public void mostrarTodos() {
        float colorIcono []={0f, 60f, 120f, 180f, 240f, 320f};

        if(lugar3==0)
        {
            List<Lugar> lstLugar = LogicLugar.listaLugares(this);
            for (Lugar p : lstLugar) {
                nuevaPosicion = new LatLng(p.getLatitud(), p.getLongitud());
                mMap.addMarker(new MarkerOptions().position(nuevaPosicion).snippet(p.getLatitud()+ ","+ p.getLongitud()).title(p.getNombre()).icon(BitmapDescriptorFactory.defaultMarker(colorIcono[p.getCategoria()])));
            }
        }
        else
        {
            List<Lugar> lstLugar = LogicLugar.listaLugares3(this, App.lugar3);
            for (Lugar p : lstLugar)
            {
                nuevaPosicion=new LatLng(p.getLatitud(),p.getLongitud());
                mMap.addMarker(new MarkerOptions().position(nuevaPosicion).snippet(p.getLatitud()+ ","+ p.getLongitud()).title(p.getNombre()).icon(BitmapDescriptorFactory.defaultMarker(colorIcono[p.getCategoria()])));
            }
        }
    }
    }

