package com.mintegral.mintegralmopubdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mintegral.mintegralmopubdemo.R;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubView;

public class BannerActivity extends Activity implements MoPubView.BannerAdListener {

    private MoPubView moPubView;
    private final String TAG = "BannerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        moPubView = (MoPubView) findViewById(R.id.banner_mopubview);
        moPubView.setAdUnitId("6fcc10326467485fb4a282d5f504a629"); // Enter your Ad Unit ID from www.mopub.com
//        moPubView.setAdSize(MoPubView.MoPubAdSize.HEIGHT_50); // Call this if you are not setting the ad size in XML or wish to use an ad size other than what has been set in the XML. Note that multiple calls to `setAdSize()` will override one another, and the MoPub SDK only considers the most recent one.
        moPubView.setBannerAdListener(this);

        Button load = findViewById(R.id.banner_load_btn);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moPubView.loadAd();
            }
        });

    }

    @Override
    public void onBannerLoaded(MoPubView banner) {
        Log.e(TAG, "onBannerLoaded: " );
    }

    @Override
    public void onBannerFailed(MoPubView banner, MoPubErrorCode errorCode) {
        Log.e(TAG, "onBannerFailed: "+errorCode );
        Toast.makeText(this, "onBannerFailed: "+errorCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBannerClicked(MoPubView banner) {
        Log.e(TAG, "onBannerClicked: ");
    }

    @Override
    public void onBannerExpanded(MoPubView banner) {

    }

    @Override
    public void onBannerCollapsed(MoPubView banner) {

    }

    @Override
    public void onBackPressed() {
        moPubView.destroy();
        finish();
    }
}
