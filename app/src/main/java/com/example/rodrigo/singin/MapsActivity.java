package com.example.rodrigo.singin;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this) // Interface ConnectionCallbacks
                    .addOnConnectionFailedListener(this) //Interface OnConnectionFailedListener
                    .addApi(LocationServices.API) // Vamos a API do LocationServices
                    .build();
        }
    }


    public void selectAnuncio(){
        final List<LatLng> novo = new ArrayList<LatLng>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("produto");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Produto anuncio = dataSnapshot.getValue(Produto.class);
                System.out.println("Olha aqui"+ anuncio);



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        // pegamos a ultima localização
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            if (mMap != null) {
                // Criamos o LatLng através do Location
                 LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title("Minha Posição"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
                latLng = new LatLng(mLastLocation.getLatitude()+0.00002, mLastLocation.getLongitude()-0.0006);
                mMap.addMarker(new MarkerOptions().position(latLng).title("Minha Posição"));
                latLng = new LatLng(mLastLocation.getLatitude()+0.0006, mLastLocation.getLongitude()-0.00010);
                mMap.addMarker(new MarkerOptions().position(latLng).title("Minha Posição"));
                latLng = new LatLng(mLastLocation.getLatitude()+0.0002, mLastLocation.getLongitude()+0.0008);
                mMap.addMarker(new MarkerOptions().position(latLng).title("Minha Posição"));
                latLng = new LatLng(mLastLocation.getLatitude()-0.0009, mLastLocation.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title("Minha Posição"));
                // Um zoom no mapa para a seua posição atual...
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));

            }

        }

    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }



    public void abriranuncio(View view) {
        Login ko = new Login();
        int i = ko.i;

        if (i == 0) {
            Toast.makeText(MapsActivity.this, "Necessario efetuar login", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, Anuncio.class));
        }


    }
}