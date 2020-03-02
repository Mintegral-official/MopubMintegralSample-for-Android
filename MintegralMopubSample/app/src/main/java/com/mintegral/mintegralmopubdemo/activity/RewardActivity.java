package com.mintegral.mintegralmopubdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.mintegral.mintegralmopubdemo.R;
import com.mopub.common.MoPubReward;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubRewardedVideoListener;
import com.mopub.mobileads.MoPubRewardedVideoManager;
import com.mopub.mobileads.MoPubRewardedVideos;

import java.util.Set;

public class RewardActivity extends Activity implements View.OnClickListener{


    private Button btn_load,btn_show;

    private String YOUR_AD_UNIT_ID = "24d5df508d54429ca530b9dc2a14c01e";

    private String YOUR_PACKAGE_NAME = "com.mintegral.mintegralmopubdemo";

    private String YOUR_CUSTOM_ID = "your custom Id";

    private MoPubRewardedVideoListener rewardedVideoListener;
    String TAG = RewardActivity.class.getSimpleName();

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button_load:
                    loadRewardedVideo();

                break;
            case R.id.button_show:
                    userClickedToWatchAd();
                break;
        }

    }



    private void loadRewardedVideo() {
        MoPubRewardedVideos.loadRewardedVideo(YOUR_AD_UNIT_ID, new MoPubRewardedVideoManager.RequestParameters("keywords", null,
                null));
    }

    private void userClickedToWatchAd() {
        MoPubRewardedVideos.showRewardedVideo(YOUR_AD_UNIT_ID);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

        YOUR_AD_UNIT_ID = getResources().getString(R.string.banner_placementid);

        if (YOUR_AD_UNIT_ID.equals("your reward  unit id of mopub")){
            Log.e(TAG, "please input your reward  unit id of mopub in res/values/string.xml " );
            return;
        }


        btn_load = (Button) findViewById(R.id.button_load);
        btn_load.setOnClickListener(this);

        btn_show = (Button) findViewById(R.id.button_show);
        btn_show.setOnClickListener(this);

        //
//        MoPubRewardedVideoManager.init(this, new MintegralRewardVideo.MintegralMediationSettings(YOUR_PACKAGE_NAME));
        MoPubRewardedVideoManager.updateActivity(this);//当mopub内部的activity被回收的时候，通过该方法将新的activity重新传入

        rewardedVideoListener = new MoPubRewardedVideoListener() {
            @Override
            public void onRewardedVideoLoadSuccess(String adUnitId) {
                Log.e("mvtest","====dev  onRewardedVideoLoadSuccess");
                Toast.makeText(RewardActivity.this,"load sucess",Toast.LENGTH_LONG).show();
                // Called when the adUnitId has loaded. At this point you should be able to call MoPub.showRewardedVideo(String) to show the video
            }

            @Override
            public void onRewardedVideoLoadFailure(String adUnitId, MoPubErrorCode errorCode) {
                Log.e("mvtest","====dev  onRewardedVideoLoadFailure");
                Toast.makeText(RewardActivity.this,"load failure "+errorCode,Toast.LENGTH_LONG).show();
                // Called when a video fails to load for the given ad unit id. The provided error code will provide more insight into the reason for the failure to load.
            }

            @Override
            public void onRewardedVideoStarted(String adUnitId) {
                // Called when a rewarded video starts playing.
                Toast.makeText(RewardActivity.this,"video start",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRewardedVideoPlaybackError(String adUnitId, MoPubErrorCode errorCode) {
                Toast.makeText(RewardActivity.this,"play error",Toast.LENGTH_LONG).show();
                //  Called when there is an error during video playback.
            }

            @Override
            public void onRewardedVideoClosed(String adUnitId) {
                Toast.makeText(RewardActivity.this,"video close",Toast.LENGTH_LONG).show();
                // Called when a rewarded video is closed. At this point your application should resume.
            }

            @Override
            public void onRewardedVideoCompleted(Set adUnitIds, MoPubReward reward) {
                Toast.makeText(RewardActivity.this,"play completed",Toast.LENGTH_LONG).show();
                // Called when a rewarded video is completed and the user should be rewarded.
                // You can query the reward object with boolean isSuccessful(), String getLabel(), and int getAmount().
            }


            @Override
            public void onRewardedVideoClicked( String s) {
                Toast.makeText(RewardActivity.this,"Click",Toast.LENGTH_LONG).show();
            }
        };

        MoPubRewardedVideos.setRewardedVideoListener(rewardedVideoListener);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    // The following methods are required for Chartboost rewarded video mediation
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onRestart() {
        super.onRestart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
