package com.mintegral.mintegralmopubdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.mintegral.mintegralmopubdemo.activity.BannerActivity;
import com.mintegral.mintegralmopubdemo.activity.MintegralInterstitialVideoNativeActivity;
import com.mintegral.mintegralmopubdemo.activity.MintegralNativeActivity;
import com.mintegral.mintegralmopubdemo.activity.RewardActivity;
import com.mintegral.msdk.MIntegralConstans;
import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.privacy.ConsentDialogListener;
import com.mopub.common.privacy.PersonalInfoManager;
import com.mopub.mobileads.MoPubErrorCode;

import static com.mopub.common.logging.MoPubLog.LogLevel.DEBUG;
import static com.mopub.common.logging.MoPubLog.LogLevel.INFO;


public class MainActivity extends Activity implements OnClickListener{


    Button btn_reward_ad, btn_native_listview,btn_native_normal,interstitialBtn,load_native_video,interstitialRewardBtn,load_normal_banner;
//    private String YOUR_AD_UNIT_ID = "44eaf01c3cee4972a6edb7e87c6d5535";
    private String YOUR_AD_UNIT_ID = "";
    private String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MIntegralConstans.DEBUG = true;

        Button banner = findViewById(R.id.xbanner);
        banner.setOnClickListener(this);



        btn_reward_ad = (Button) findViewById(R.id.reward_ad_btn);
        btn_reward_ad.setOnClickListener(this);


        interstitialBtn = (Button) findViewById(R.id.normal_interstitial);
        interstitialBtn.setOnClickListener(this);



        load_native_video = (Button) findViewById(R.id.load_native_video);
        load_native_video.setOnClickListener(this);

        YOUR_AD_UNIT_ID = getResources().getString(R.string.init_placementid);

        if (YOUR_AD_UNIT_ID.equals("any one unit id of mopub")){
            Log.e(TAG, "please input your any one  unit id of mopub in res/values/string.xml " );
            return;
        }


        final SdkConfiguration.Builder configBuilder = new SdkConfiguration.Builder(YOUR_AD_UNIT_ID);

        if (BuildConfig.DEBUG) {
            configBuilder.withLogLevel(DEBUG);
        } else {
            configBuilder.withLogLevel(INFO);
        }

        MoPub.initializeSdk(this, configBuilder.build(), new SdkInitializationListener() {
            @Override
            public void onInitializationFinished() {
                Log.e(TAG,"initializeSdk");
            }
        });

        final PersonalInfoManager mPersonalInfoManager = MoPub.getPersonalInformationManager();
        if(mPersonalInfoManager.shouldShowConsentDialog()){

        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                mPersonalInfoManager.loadConsentDialog(new ConsentDialogListener() {

                                                           @Override
                                                           public void onConsentDialogLoaded() {
                                                               MoPubLog.i("Consent dialog SUCCESS to load.");
                                                               if (mPersonalInfoManager != null) {
                                                                   mPersonalInfoManager.showConsentDialog();
                                                               }
                                                           }

                                                           @Override
                                                           public void onConsentDialogLoadFailed( MoPubErrorCode moPubErrorCode) {
                                                               MoPubLog.i("Consent dialog failed to load.");
                                                           }
                                                       }

                );
                Looper.loop();
            }
        }).start();


    }


    @Override
    public void onClick(View v) {
        Intent mIntent = null;
        switch (v.getId()){
            case R.id.xbanner:
                mIntent = new Intent(this, BannerActivity.class);
                break;

            case R.id.reward_ad_btn:
                mIntent = new Intent(this, RewardActivity.class);
                break;

            case R.id.normal_interstitial:
                mIntent = new Intent(this, MintegralInterstitialVideoNativeActivity.class);
                break;

            case R.id.load_native_video:
                mIntent = new Intent(this, MintegralNativeActivity.class);
                break;

            default:
                Toast.makeText(this,"can't find the view",Toast.LENGTH_LONG).show();
        }
        if(mIntent != null){
            startActivity(mIntent);
        }

    }
}
