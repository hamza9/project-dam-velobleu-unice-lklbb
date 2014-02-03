package unice.dam.koubi.lebourblanc;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

public class SplashscreenActivity extends Activity{


    private ProgressBar mProgress;
    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();
    private static AsyncHttpClient client = new AsyncHttpClient();
    //private ArrayList<Station> listPars;
    
    private MainApplication main;
    private ImageView img;
    private ProgressBar progress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);
		
		main = (MainApplication) getApplication();	
		img = (ImageView) findViewById(R.id.imageView1);
		progress = (ProgressBar) findViewById(R.id.progressBar1);
		
		String url = "http://dam.lanoosphere.com/getVelos";

		RequestParams params = new RequestParams();
		
		client.get(url, params, new TextHttpResponseHandler() {
			@Override
			public void onSuccess(String xmlString) {
				String idStation, nomStation, adresse = null, dispo = null, longitude = null, latitude = null
						, capaTot = null, capaDisp = null, plaDisp = null, veloDisp = null; 						
				
				StationParser parser = new StationParser();
				main.stations = parser.parse(xmlString);
				
				progress.setVisibility(View.INVISIBLE);
				
			    Animation a = AnimationUtils.loadAnimation(SplashscreenActivity.this, R.anim.sortie);
			    a.setFillAfter(true);
			    a.setAnimationListener(new AnimationListener() {				
					@Override
					public void onAnimationStart(Animation animation) {
					}
					
					@Override
					public void onAnimationRepeat(Animation animation) {
					}
					
					@Override
					public void onAnimationEnd(Animation animation) {
						Intent intent = new Intent(SplashscreenActivity.this, MainActivity.class);
					    startActivity(intent);
					    finish();					
					}
				});	
			    
			    img.startAnimation(a);
			}
		});	     
    }
	
	/*private class myTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... arg0) {
			ArrayList<Station> stations = null;
	    	try {
	    		
			} catch (Exception e) {
				e.printStackTrace();
			}
			Log.i("doInBackground", "Stations loaded");
			return null;
		}
		
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			//img.startAnimation(AnimationUtils.loadAnimation(SplashscreenActivity.this, R.anim.sortie));
			
			
		    
		}
	}*/
	
}
