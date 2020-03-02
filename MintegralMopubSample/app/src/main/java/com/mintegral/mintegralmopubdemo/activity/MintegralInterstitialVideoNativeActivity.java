package com.mintegral.mintegralmopubdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mintegral.mintegralmopubdemo.R;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;

/**
 * Created by songjunjun on 17/8/31.
 */

public class MintegralInterstitialVideoNativeActivity extends Activity implements View.OnClickListener,MoPubInterstitial.InterstitialAdListener {


    private MoPubInterstitial mMoPubInterstitial;
    Button interstitialLoadBtn,interstitialShowBtn;
    String mopubAdUnitId = "dcc8d27a742b4df48fc99236e784cb18";
    String TAG = MintegralInterstitialVideoNativeActivity.class.getSimpleName();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interstitial_activity);

        mopubAdUnitId = getResources().getString(R.string.interstitial_placementid);

        if (mopubAdUnitId.equals("your interstitial  unit id of mopub")){
            Log.e(TAG, "please input your interstitial  unit id of mopub in res/values/string.xml " );
            return;
        }


        mMoPubInterstitial = new MoPubInterstitial(this, mopubAdUnitId);
        mMoPubInterstitial.setInterstitialAdListener(this);
        initView();
        setListener();
    }
    private void initView(){
        interstitialLoadBtn = (Button) findViewById(R.id.button_load);
        interstitialShowBtn = (Button) findViewById(R.id.button_show);
    }

    private void setListener(){
        if (interstitialLoadBtn != null){
            interstitialLoadBtn.setOnClickListener(this);
        }
        if(interstitialShowBtn != null){
            interstitialShowBtn.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_load:
                if(mMoPubInterstitial != null){
                    mMoPubInterstitial.load();
                }
                break;
            case R.id.button_show:
                if (mMoPubInterstitial!=null && mMoPubInterstitial.isReady()){
                    Toast.makeText(getApplicationContext(),"iv show",Toast.LENGTH_SHORT).show();
                    mMoPubInterstitial.show();
                }else{
                    mMoPubInterstitial.load();
                    Toast.makeText(getApplicationContext(),"iv ads not ready",Toast.LENGTH_SHORT).show();
                    Log.e("mopub-mv", "ads not ready invoke load");
                }
                break;
        }
    }


    @Override
    public void onInterstitialDismissed(MoPubInterstitial interstitial) {
        Toast.makeText(getApplicationContext(),"iv onInterstitialDismissed",Toast.LENGTH_SHORT).show();
        Log.e("mopub-mv", "onInterstitialDismissed");
    }

    @Override
    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
        Toast.makeText(getApplicationContext(),"iv onInterstitialFailed",Toast.LENGTH_SHORT).show();
        Log.e("mopub-mv", "onInterstitialFailed");
    }

    @Override
    public void onInterstitialClicked(MoPubInterstitial interstitial) {
        Toast.makeText(getApplicationContext(),"iv onInterstitialClicked",Toast.LENGTH_SHORT).show();
        Log.e("mopub-mv", "onInterstitialClicked");
    }

    @Override
    public void onInterstitialLoaded(MoPubInterstitial interstitial) {
        Toast.makeText(getApplicationContext(),"iv onInterstitialLoaded",Toast.LENGTH_SHORT).show();
        Log.e("mopub-mv", "onInterstitialLoaded");
    }

    @Override
    public void onInterstitialShown(MoPubInterstitial interstitial) {
        Toast.makeText(getApplicationContext(),"iv onInterstitialShown",Toast.LENGTH_SHORT).show();
        Log.e("mopub-mv", "onInterstitialShown");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMoPubInterstitial.destroy();
        mMoPubInterstitial=null;
    }
}
