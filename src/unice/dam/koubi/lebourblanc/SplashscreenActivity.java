package unice.dam.koubi.lebourblanc;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Xml;
import android.widget.ProgressBar;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

public class SplashscreenActivity extends Activity{


    private ProgressBar mProgress;
    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();
    private static AsyncHttpClient client = new AsyncHttpClient();
    private ArrayList<Station> listPars;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);
		
	
	     mProgress = (ProgressBar) findViewById(R.id.progressBar2);
	
	     // Start lengthy operation in a background thread
	     new Thread(new Runnable() {
	         public void run() {
	             while (mProgressStatus < 100) {
	                 mProgressStatus = doWork();
	
	                 // Update the progress bar
	                 mHandler.post(new Runnable() {
	                     public void run() {
	                         mProgress.setProgress(mProgressStatus);
	                     }
	                 });
	             }
	             startActivity(new Intent(SplashscreenActivity.this, MainActivity.class));
	         }

			private int doWork() {
				// TODO Auto-generated method stub
				String url = "dam.lanoosphere.com/getVelos";

				RequestParams params = new RequestParams();
				
				client.get(url, params, new TextHttpResponseHandler() {
					@Override
					public void onSuccess(String xmlString) {
						//Log.v("td4", "xmlString ="+ xmlString);
						listPars = new ArrayList<Station>();
						// cancel = false;
						try {
							XmlPullParser parser = Xml.newPullParser();
							parser.setInput(new StringReader(xmlString));

							parser.nextTag();
							parser.require(XmlPullParser.START_TAG, null, "site");
						//	int eventType = parser.getEventType();
							String idStation, nomStation, adresse = null, dispo = null, longitude = null, latitude = null
									, capaTot = null, capaDisp = null, plaDisp = null, veloDisp = null; 
							// if (!cancel) {
							while (parser.nextTag() == XmlPullParser.START_TAG) {
								
									//Log.v("td4", "eventType= " + parser.getName());
									if (parser.getName().equals("stand")) {
										idStation = parser.getAttributeValue(null, "id");
										nomStation = parser.getAttributeValue(null, "first_name");
										if(parser.getName().equals("wcom")){
											adresse = parser.nextText();
										}
										else if(parser.getName().equals("disp")){
											dispo = parser.nextText();
										}
										else if(parser.getName().equals("lng")){
											longitude = parser.nextText();
										}
										else if(parser.getName().equals("lat")){
											latitude = parser.nextText();										
										}
										else if(parser.getName().equals("tc")){
											capaTot = parser.nextText();
										}
										else if(parser.getName().equals("ac")){
											capaDisp = parser.nextText();
										}
										else if(parser.getName().equals("ap")){
											plaDisp = parser.nextText();										
										}
										else if(parser.getName().equals("ab")){
											veloDisp = parser.nextText();
										}
										
										Station sta = new Station(Integer.parseInt(idStation), nomStation
												, adresse, Integer.parseInt(dispo), Double.parseDouble(longitude), Double.parseDouble(latitude)
												, Integer.parseInt(capaTot), Integer.parseInt(capaDisp), Integer.parseInt(plaDisp)
												, Integer.parseInt(veloDisp));
										listPars.add(sta);
									} 

									parser.nextTag();
							}
							

						} catch (XmlPullParserException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				return 0;
			}
	     }).start();
	
	    }
	
}
