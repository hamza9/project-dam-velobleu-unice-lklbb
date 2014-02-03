package unice.dam.koubi.lebourblanc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class StationActivity extends Activity {
	
	private MainApplication main;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_station);
		
		main = (MainApplication) getApplicationContext();
		
		Intent myIntent = getIntent();
		int num = myIntent.getIntExtra("num", 0);
		
		Station station = main.stations.get(num);
		
		TextView nom = (TextView) findViewById(R.id.nom);
		TextView com = (TextView) findViewById(R.id.com);
		TextView capTot = (TextView) findViewById(R.id.capTot);
		TextView capDisp = (TextView) findViewById(R.id.capDisp);
		TextView place = (TextView) findViewById(R.id.place);
		TextView velo = (TextView) findViewById(R.id.velo);
		
		nom.setText(station.getNomStation());
		com.setText(station.getAdresse());
		capTot.setText(station.getCapaTot() + "");
		capDisp.setText(station.getCapaDisp() + "");
		place.setText(station.getPlaDisp() + "");
		velo.setText(station.getVeloDisp() + "");
		
		
	}
	

}
