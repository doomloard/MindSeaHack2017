package edu.school.nick.climatemaps;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
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

import java.util.ArrayList;
import java.util.Arrays;

public class ClimateMap extends FragmentActivity implements OnMapReadyCallback, SeekBar.OnSeekBarChangeListener {

    private GoogleMap climateMap;

    private android.support.v7.widget.Toolbar toolbar;
    private TextView toolbarTitleTextView;

    private RelativeLayout parameterCell;

    private Spinner parameterSpinner;
    private Spinner locationSpinner;
    private Spinner subparamSpinner;
    private SeekBar seekBar;

    private TextView subParameterTextView;

    private boolean subparamsVisible;

    private ArrayList<String> parameters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climate_map);

        parameterCell = (RelativeLayout) findViewById(R.id.activity_climate_map_parameters);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.activity_climate_map_toolbar);
        toolbarTitleTextView = (TextView) toolbar.findViewById(R.id.toolbar_text_view);
        toolbarTitleTextView.setText("Climate App");
        toolbarTitleTextView.setTextColor(Color.BLACK);

        subParameterTextView = (TextView) parameterCell.findViewById(R.id.cell_parameters_sub_parameter_text_view);

        parameters = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Parameters)));

        locationSpinner = (Spinner) parameterCell.findViewById(R.id.cell_parameters_location_spinner);
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Update the map
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        parameterSpinner = (Spinner) parameterCell.findViewById(R.id.cell_parameters_parameter_spinner);
        parameterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Update the map
                // Call configureSubParameters() with your parameter string which will update your subparameters view
                configureSubParameters(parameters.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        subparamSpinner = (Spinner) parameterCell.findViewById(R.id.cell_parameters_sub_parameter_spinner);
        subparamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Update the map
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        seekBar = (SeekBar) parameterCell.findViewById(R.id.cell_parameters_seek_bar);
        seekBar.setOnSeekBarChangeListener(this);

        configureParameters();
        configureSubParameters("");
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

    private void configureSubParameters(String parameter) {
        ArrayAdapter<CharSequence> subParametersAdapter;

        switch (parameter) {
            case "Temperature":
                subParametersAdapter = ArrayAdapter.createFromResource(this, R.array.Temperature_Subparams, R.layout.custom_spinner_item);
                break;
            case "Water":
                subParametersAdapter = ArrayAdapter.createFromResource(this, R.array.Water_Subparams, R.layout.custom_spinner_item);
                break;
            default:
                subParametersAdapter = null;
        }

        if (subParametersAdapter != null) {
            subParametersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            subparamSpinner.setAdapter(subParametersAdapter);

            subparamsVisible = true;
            updateView();
            return;
        }

        subparamsVisible = false;
        updateView();

    }

    private void configureLocations() {
        ArrayAdapter<CharSequence> locationAdapter = ArrayAdapter.createFromResource(this, R.array.Locations, R.layout.custom_spinner_item);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);
    }

    private void updateView() {
        if (subparamSpinner != null) {
            subparamSpinner.setVisibility(subparamsVisible ? View.VISIBLE : View.INVISIBLE);
        }

        if (subParameterTextView != null) {
            subParameterTextView.setVisibility(subparamsVisible ? View.VISIBLE : View.INVISIBLE);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // If the slider updates, you'll get the callback here, so update map
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

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
