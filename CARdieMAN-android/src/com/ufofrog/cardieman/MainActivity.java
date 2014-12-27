package com.ufofrog.cardieman;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.ufofrog.cardieman.game.GdxGame;
import com.ufofrog.core.ActionResolver;


public class MainActivity extends AndroidApplication implements ActionResolver {
	
	private static final String AD_UNIT_ID_BANNER = "ca-app-pub-8815167150539395/8726260461";
	private static final String AD_UNIT_ID_INTERSTITIAL = "ca-app-pub-8815167150539395/7303559663";
	protected AdView adView;
	protected View gameView;
	private InterstitialAd interstitialAd;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;

        // Do the stuff that initialize() would do for you
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        
        RelativeLayout layout = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);
        
        AdView admobView = createAdView();
        layout.addView(admobView);
        View gameView = createGameView(cfg);
        layout.addView(gameView);
        
        setContentView(layout);
        startAdvertising(admobView);
        
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(AD_UNIT_ID_INTERSTITIAL);
        interstitialAd.setAdListener(new AdListener() {
	        @Override
	        public void onAdLoaded() {
		        Toast.makeText(getApplicationContext(), "Finished Loading Interstitial", Toast.LENGTH_SHORT).show();
	        }
	        @Override
	        public void onAdClosed() {
		        Toast.makeText(getApplicationContext(), "Closed Interstitial", Toast.LENGTH_SHORT).show();
	        }
	    });        
        
        // initialize(new GdxGame(), cfg);
    }
    
    private void startAdvertising(AdView admobView) {
    	 AdRequest adRequest = new AdRequest.Builder().build();
    	 adView.loadAd(adRequest);		
	}

	private View createGameView(AndroidApplicationConfiguration cfg) {
		 gameView = initializeForView(new GdxGame(this), cfg);
		 RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		 params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		 params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		 params.addRule(RelativeLayout.BELOW, adView.getId());
		 gameView.setLayoutParams(params);
		 return gameView;
	}

	private AdView createAdView() {
		 adView = new AdView(this);
		 adView.setAdSize(AdSize.SMART_BANNER);
		 adView.setAdUnitId(AD_UNIT_ID_BANNER);
		 adView.setId(12345); // this is an arbitrary id, allows for relative positioning in createGameView()
		 RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		 params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		 params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		 adView.setLayoutParams(params);
		 adView.setBackgroundColor(Color.BLACK);
		 return adView;
	}

	@Override
	public void showOrLoadInterstital() {
		/*
		// TODO Auto-generated method stub
		try {
			runOnUiThread(new Runnable() {
				public void run() {
					if (interstitialAd.isLoaded()) {
						interstitialAd.show();
						Toast.makeText(getApplicationContext(),
								"Showing Interstitial", Toast.LENGTH_SHORT)
								.show();
					} else {
						AdRequest interstitialRequest = new AdRequest.Builder()
								.build();
						interstitialAd.loadAd(interstitialRequest);
						Toast.makeText(getApplicationContext(),
								"Loading Interstitial", Toast.LENGTH_SHORT)
								.show();
					}
				}
			});
		} catch (Exception e) {
		}
		*/
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (adView != null)
			adView.resume();
	}

	@Override
	public void onPause() {
		if (adView != null)
			adView.pause();
		super.onPause();
	}

	@Override
	public void onDestroy() {
		if (adView != null)
			adView.destroy();
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {

		final Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		Button b1 = new Button(this);
		b1.setText("Quit");
		b1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		ll.addView(b1);
		dialog.setContentView(ll);
		dialog.show();
	}
}