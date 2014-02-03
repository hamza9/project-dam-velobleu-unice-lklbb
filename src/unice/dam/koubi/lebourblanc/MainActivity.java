package unice.dam.koubi.lebourblanc;

import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private LocationManager locationManager;
	private Location location;
	private LocationListener locationListener;
	private MainApplication main;
	
	private Button btnPlace;
	private Button btnVelo;
	private Button btnDist;
	private ListView listView;
	
	private StationAdapter adap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		main = (MainApplication) getApplicationContext();
		
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		
		float[] results = new float[1];
		for (Station station : main.stations) {	        	
        	Location.distanceBetween(location.getLatitude(), location.getLongitude(), station.getLatitude(), station.getLongitude(), results);
			station.setDistance(results[0]);
		}
		
		locationListener = new LocationListener() {

			@Override
			public void onLocationChanged (Location location) {
				location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				float[] results = new float[1];
				for (Station station : main.stations) {	        	
		        	Location.distanceBetween(location.getLatitude(), location.getLongitude(), station.getLatitude(), station.getLongitude(), results);
					station.setDistance(results[0]);
				}
				listView.setAdapter(adap);
			}

			@Override
			public void onProviderDisabled(String arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderEnabled(String arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				// TODO Auto-generated method stub
				
			}
		};
		
		
		btnPlace = (Button) findViewById(R.id.buttonPlaces);
		btnVelo = (Button) findViewById(R.id.buttonVelo);
		btnDist = (Button) findViewById(R.id.buttonDist);
		listView = (ListView) findViewById(R.id.listViewStation);
		
		btnVelo.setBackground(getResources().getDrawable(R.drawable.btn_activate));
		
		adap = new StationAdapter(MainActivity.this);
		listView.setAdapter(adap);
		
		btnPlace.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				btnVelo.setBackground(getResources().getDrawable(R.drawable.btn));
				btnPlace.setBackground(getResources().getDrawable(R.drawable.btn_activate));
				btnDist.setBackground(getResources().getDrawable(R.drawable.btn));
				
				Collections.sort(main.stations, Station.placeComparator());
				
				adap.notifyDataSetChanged();
				
				Log.e("sort", "Place");
			}
		});
		
		btnVelo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				btnVelo.setBackground(getResources().getDrawable(R.drawable.btn_activate));
				btnPlace.setBackground(getResources().getDrawable(R.drawable.btn));
				btnDist.setBackground(getResources().getDrawable(R.drawable.btn));
				
				Collections.sort(main.stations, Station.veloComparator());
				
				adap.notifyDataSetChanged();
				
				Log.e("sort", "Velo");
			}
		});

		btnDist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				btnVelo.setBackground(getResources().getDrawable(R.drawable.btn));
				btnPlace.setBackground(getResources().getDrawable(R.drawable.btn));
				btnDist.setBackground(getResources().getDrawable(R.drawable.btn_activate));
				
				Collections.sort(main.stations, Station.distanceComparator());
				
				adap.notifyDataSetChanged();
				
				Log.e("sort", "Distance");
			}
		});		
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(MainActivity.this, StationActivity.class);
				intent.putExtra("num", position);
				startActivity(intent);
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates
		(LocationManager.GPS_PROVIDER, 0, 100, locationListener);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(locationListener);
	}

}
