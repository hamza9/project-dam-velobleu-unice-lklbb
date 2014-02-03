package unice.dam.koubi.lebourblanc;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StationAdapter extends BaseAdapter{
	
	private LayoutInflater inflater;
	private MainApplication main;
	
	/**
	 * Couleurs de fond des lignes de la liste
	 */
	private static final int[] BACKGROUND_GREYS = { 0xffFAFAFA,
			0xffE6E6E6 };
	
	public StationAdapter(Context context){
		inflater = (LayoutInflater)context.getSystemService
			      (Context.LAYOUT_INFLATER_SERVICE);
		main = (MainApplication) context.getApplicationContext();
	}

	@Override
	public int getCount() {
		return main.stations.size();
	}

	@Override
	public Station getItem(int arg0) {		
		return main.stations.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_station,
					parent, false);
		}
		convertView.setBackgroundColor(BACKGROUND_GREYS[position
				% BACKGROUND_GREYS.length]);
		Station station = getItem(position);

		TextView nom = (TextView) convertView.findViewById(R.id.nom);
		TextView com = (TextView) convertView.findViewById(R.id.com);
		TextView velo = (TextView) convertView.findViewById(R.id.velo);
		TextView place = (TextView) convertView.findViewById(R.id.place);
		TextView dist = (TextView) convertView.findViewById(R.id.dist);
		
		int veloDisp = station.getVeloDisp();
		int plaDisp = station.getPlaDisp();
		double distance = station.getDistance();

		nom.setText(station.getNomStation());
		com.setText(station.getAdresse());
		velo.setText(veloDisp + " vélos");
		place.setText(plaDisp + " places");
		
		dist.setText(Utils.getDistance(distance));
		
		if(veloDisp > 3)
			velo.setTextColor(Color.rgb(4, 130, 4));
		else if(veloDisp == 0)
			velo.setTextColor(Color.RED);
		else
			velo.setTextColor(Color.rgb(255, 165, 0));
		
		if(plaDisp > 3)
			place.setTextColor(Color.rgb(4, 130, 4));
		else if(plaDisp == 0)
			place.setTextColor(Color.RED);
		else
			place.setTextColor(Color.rgb(255, 165, 0));
		
		if(distance <= 300)
			dist.setTextColor(Color.rgb(4, 130, 4));
		else if(distance > 800)
			dist.setTextColor(Color.RED);
		else
			dist.setTextColor(Color.rgb(255, 165, 0));

		return convertView;
	}

}
