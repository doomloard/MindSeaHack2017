package edu.school.nick.climatemaps;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;

public class ClimateMap extends FragmentActivity implements OnMapReadyCallback {
    public ArrayList<ClimateData> masterList = new ArrayList<ClimateData>();

    public ArrayList<ClimateData> temperatureSummer = new ArrayList<ClimateData>();
    public ArrayList<ClimateData> temperatureWinter = new ArrayList<ClimateData>();
    public ArrayList<ClimateData> temperatureFall = new ArrayList<ClimateData>();
    public ArrayList<ClimateData> temperatureSpring = new ArrayList<ClimateData>();
    public ArrayList<ClimateData> temperatureAnnual = new ArrayList<ClimateData>();
    public ArrayList<ClimateData> waterShortage = new ArrayList<ClimateData>();
    public ArrayList<ClimateData> waterSurplus = new ArrayList<ClimateData>();
    public ArrayList<ClimateData> precipitationSummer = new ArrayList<ClimateData>();
    public ArrayList<ClimateData> precipitationWinter = new ArrayList<ClimateData>();
    public ArrayList<ClimateData> precipitationSpring = new ArrayList<ClimateData>();
    public ArrayList<ClimateData> precipitaitonFall = new ArrayList<ClimateData>();
    public ArrayList<ClimateData> precipitationAnnual = new ArrayList<ClimateData>();
    public ArrayList<ClimateData> coldDays = new ArrayList<ClimateData>();
    public ArrayList<ClimateData> hotDays = new ArrayList<ClimateData>();

    private GoogleMap mMap;
    int radius = 1000;
    ArrayList<CircleOptions> circleOptions = new ArrayList<CircleOptions>(Arrays.asList(
            new CircleOptions()
                    .radius(radius)   //set radius in meters
                    .fillColor(0x7FFF0000)  //Transparent Greem
                    .strokeColor(0x10000000)
                    .strokeWidth(5),
            new CircleOptions()
                    .radius(radius)   //set radius in meters
                    .fillColor(0x7FFF3300)  //Transparent Greem
                    .strokeColor(0x10000000)
                    .strokeWidth(5),
            new CircleOptions()
                    .radius(radius)   //set radius in meters
                    .fillColor(0x7Fff6600)  //Transparent Greem
                    .strokeColor(0x10000000)
                    .strokeWidth(5),
            new CircleOptions()
                    .radius(radius)   //set radius in meters
                    .fillColor(0x7Fff9900)  //Transparent Greem
                    .strokeColor(0x10000000)
                    .strokeWidth(5),
            new CircleOptions()
                    .radius(radius)   //set radius in meters
                    .fillColor(0x7FFFCC00)  //Transparent Greem
                    .strokeColor(0x10000000)
                    .strokeWidth(5),
            new CircleOptions()
                    .radius(radius)   //set radius in meters
                    .fillColor(0x7FFFFF00 )  //Transparent Greem
                    .strokeColor(0x10000000)
                    .strokeWidth(5),
            new CircleOptions()
                    .radius(radius)   //set radius in meters
                    .fillColor(0x7Fccff00)  //Transparent Greem
                    .strokeColor(0x10000000)
                    .strokeWidth(5),
            new CircleOptions()
                    .radius(radius)   //set radius in meters
                    .fillColor(0x7F99ff00)  //Transparent Greem
                    .strokeColor(0x10000000)
                    .strokeWidth(5),
            new CircleOptions()
                    .radius(radius)   //set radius in meters
                    .fillColor(0x7F66ff00)  //Transparent Greem
                    .strokeColor(0x10000000)
                    .strokeWidth(5),
            new CircleOptions()
                    .radius(radius)   //set radius in meters
                    .fillColor(0x7F33ff00)  //Transparent Greem
                    .strokeColor(0x10000000)
                    .strokeWidth(5),
            new CircleOptions()
                    .radius(radius)   //set radius in meters
                    .fillColor(0x7F00FF00 )  //Transparent Greem
                    .strokeColor(0x10000000)
                    .strokeWidth(5)
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climate_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap = googleMap;
        LatLng halifax = new LatLng(44.648763500, -63.575238700);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(halifax));



        LatLng latLng = new LatLng(44.6488, -63.5752);



    }

    public void updateCircles(ArrayList<ClimateData> list, GoogleMap mMap, String location){
        mMap.clear();
        int min = getMin(list);
        int max = getMax(list);
        for(ClimateData data: list){
            if(data.GET_region.equals(location)){
                mMap.addCircle(circleOptions.get(getColorValue(data, min, max)).center(getLatLng(data)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(getLatLng(data)));
            }
        }
    }

    public int getMin(ArrayList<ClimateData> list){
        int min = 100000;
        for(ClimateData data: list){
            if(data.GET_value()<min)
                min = data.GET_value();
        }
        return min;
    }
    public int getMax(ArrayList<ClimateData> list){
        int max = -10000;
        for(ClimateData data: list){
            if(data.GET_value()>max)
                max = data.GET_value();
        }
        return max;
    }
    //assign a value from 0 - 10
    public int getColorValue(ClimateData data, int listMin, int listMax){
        int value = data.GET_value();
        if(value == listMin)
            return 0;
        else
            return (int) Math.floor((value-listMin)/(listMax-listMin)*10);
    }

    public LatLng getLatLng(ClimateData data){
        String region = data.GET_region;
        switch(region) {
            case("Amherst"):
                return new LatLng(45.816667000, -64.216720600);
                break;
            case("Annapolis"):
                return new LatLng(44.742226000, -65.515822000);
                break;
            case("Annapolis Valley"):
                return new LatLng(44.916666700, -65.166666700);
                break;
            case("Cape Breton West"):
                return new LatLng(46.029337000, -60.236447200);
                break;
            case("Guysborough"):
                return new LatLng(45.390607500, -61.498962700);
                break;
            case("HRM"):
                return new LatLng(44.648763500, -63.575238700);
                break;
            case("Kentville"):
                return new LatLng(45.076911500, -64.494473500);
                break;
            case("Liverpool"):
                return new LatLng(44.032977200, -64.717678300);
                break;
            case("Lunengurg"):
                return new LatLng(44.377005300, -64.318835400);
                break;
            case("Pictou-Antigonish"):
                return new LatLng(45.679332000, -62.720603000);
                break;
            case("Sydney"):
                return new LatLng(46.136789900, -60.194224000);
                break;
            case("Truro"):
                return new LatLng(45.365773300, -63.286940700);
                break;
            case("Yarmouth"):
                return new LatLng(43.837457600, -66.117382000);
                break;
            default:
                return null;
        }
    }
    public void divideLists(ArrayList<ClimateData> list){
        String region;
        for(ClimateData data: list){
            region = data.GET_region();
            switch(region){
                case("Cold Days (Tmax < -10)"):
                    coldDays.add(data);
                    break;
                case("Hot Days (Tmax > 30)"):
                    hotDays.add(data);
                    break;
                case("Precipitation - Annual"):
                    precipitationAnnual.add(data);
                    break;
                case("Precipitation - Autumn"):
                    precipitaitonFall.add(data);
                    break;
                case("Precipitation - Spring"):
                    precipitationSpring.add(data);
                    break;
                case("Precipitation - Summer"):
                    precipitationSummer.add(data);
                    break;
                case("Precipitation - Winter"):
                    precipitationWinter.add(data);
                    break;
                case("Temperature - Annual"):
                    temperatureAnnual.add(data);
                    break;
                case("Temperature - Autumn"):
                    temperatureFall.add(data);
                    break;
                case("Temperature - Spring"):
                    temperatureSpring.add(data);
                    break;
                case("Temperature - Summer"):
                    temperatureSummer.add(data);
                    break;
                case("Temperature - Winter"):
                    temperatureWinter.add(data);
                    break;
                case("Water Deficit"):
                    waterShortage.add(data);
                    break;
                case("Water Surplus"):
                    waterSurplus.add(data);
                    break;
            }
        }
    }
}
