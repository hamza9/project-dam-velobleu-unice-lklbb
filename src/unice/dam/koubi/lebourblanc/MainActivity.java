package unice.dam.koubi.lebourblanc;

import java.util.Collections;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private LocationManager locationManager;
	private Location location;
	private LocationListener locationListener;
	private MainApplication main;
	
	private Button btnPlace;
	private Button btnVelo;
	private Button btnDist;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		main = (MainApplication) getApplicationContext();
		
		/*locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		location = locationManager.getLastKnownLocation (LocationManager.GPS_PROVIDER);
		LocationListener locationListener = new LocationListener() {
			// …
			@Override
			public void onLocationChanged (Location location) {
			// Utiliser la nouvelle position…
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
		};*/
		
		btnPlace = (Button) findViewById(R.id.buttonPlaces);
		btnVelo = (Button) findViewById(R.id.buttonVelo);
		btnDist = (Button) findViewById(R.id.buttonDist);
		
		btnPlace.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Collections.sort(main.stations, Station.placeComparator());
				
				for(Station st : main.stations)
				{
					Log.e(st.getNomStation(), st.getPlaDisp() + "");
				}
			}
		});
		
		btnVelo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Collections.sort(main.stations, Station.veloComparator());
				
				for(Station st : main.stations)
				{
					Log.e(st.getNomStation(), st.getVeloDisp() + "");
				}
			}
		});

		btnDist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Collections.sort(main.stations, Station.distanceComparator());
				
				for(Station st : main.stations)
				{
					Log.e(st.getNomStation(), st.getDistance() + "");
				}
			}
		});
		
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		/*locationManager.requestLocationUpdates
		(LocationManager.GPS_PROVIDER, 500, 0, locationListener);*/
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//locationManager.removeUpdates(locationListener);
	}

}
