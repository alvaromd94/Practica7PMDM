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
            Intent intent=new Intent(this,InformacionActivity.class);
            startActivity(intent);
        }

        return false;
    }

    public void mostrarTodos() {
        List<Lugar> lstLugar = LogicLugar.listaLugares(this);
        if (lstLugar == null) {
        } else {

            for (Lugar p : lstLugar) {
                nuevaPosicion = new LatLng(p.getLatitud(), p.getLongitud());
                if (p.getCategoria() == 1) {
                    mMap.addMarker(new MarkerOptions().position(nuevaPosicion).title(p.getNombre()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                } else if (p.getCategoria() == 2) {
                    mMap.addMarker(new MarkerOptions().position(nuevaPosicion).title(p.getNombre()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                } else if (p.getCategoria() == 3) {
                    mMap.addMarker(new MarkerOptions().position(nuevaPosicion).title(p.getNombre()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                }
                else if (p.getCategoria() == 4) {
                    mMap.addMarker(new MarkerOptions().position(nuevaPosicion).title(p.getNombre()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
                }
                else if (p.getCategoria() == 5) {
                    mMap.addMarker(new MarkerOptions().position(nuevaPosicion).title(p.getNombre()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                }
            }
        }
    }
}
