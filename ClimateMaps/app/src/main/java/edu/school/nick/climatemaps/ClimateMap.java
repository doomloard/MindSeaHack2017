package edu.school.nick.climatemaps;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ClimateMap extends FragmentActivity implements OnMapReadyCallback, SeekBar.OnSeekBarChangeListener {

    private GoogleMap climateMap;

    private android.support.v7.widget.Toolbar toolbar;
    private TextView toolbarTitleTextView;

    private RelativeLayout parameterCell;

    private Spinner parameterSpinner;
    private Spinner locationSpinner;
    private Spinner subparamSpinner;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climate_map);

        parameterCell = (RelativeLayout) findViewById(R.id.activity_climate_map_parameters);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.activity_climate_map_toolbar);
        toolbarTitleTextView = (TextView) toolbar.findViewById(R.id.toolbar_text_view);
        toolbarTitleTextView.setText("Climate App");
        toolbarTitleTextView.setTextColor(Color.BLACK);

        parameterSpinner = (Spinner) parameterCell.findViewById(R.id.cell_parameters_parameter_spinner);
        locationSpinner = (Spinner) parameterCell.findViewById(R.id.cell_parameters_location_spinner);
        subparamSpinner = (Spinner) parameterCell.findViewById(R.id.cell_parameters_sub_parameter_spinner);
        seekBar = (SeekBar) parameterCell.findViewById(R.id.cell_parameters_seek_bar);
        seekBar.setOnSeekBarChangeListener(this);

        configureParameters();
        configureSubParameters();
        configureLocations();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_climate_map_map);
        mapFragment.getMapAsync(this);
    }

    private void configureParameters() {
        ArrayAdapter<CharSequence> parametersAdapter = ArrayAdapter.createFromResource(this, R.array.Parameters, R.layout.custom_spinner_item);
        parametersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parameterSpinner.setAdapter(parametersAdapter);
    }

    private void configureSubParameters() {
        ArrayAdapter<CharSequence> subParametersAdapter = ArrayAdapter.createFromResource(this, R.array.Water_Subparams, R.layout.custom_spinner_item);
        subParametersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subparamSpinner.setAdapter(subParametersAdapter);
    }

    private void configureLocations() {
        ArrayAdapter<CharSequence> locationAdapter = ArrayAdapter.createFromResource(this, R.array.Locations, R.layout.custom_spinner_item);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        climateMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        climateMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        climateMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
