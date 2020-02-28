package com.mintegral.mintegralmopubdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.mintegral.mintegralmopubdemo.R;

import com.mopub.nativeads.MintegralAdRenderer;
import com.mopub.nativeads.MoPubNative;
import com.mopub.nativeads.NativeAd;
import com.mopub.nativeads.NativeErrorCode;
import com.mopub.nativeads.RequestParameters;
import com.mopub.nativeads.ViewBinder;

import java.util.EnumSet;

public class MintegralNativeActivity extends Activity implements MoPubNative.MoPubNativeNetworkListener{

//    MoPubAdAdapter mAdapter;
    private static final String MY_AD_UNIT_ID = "5aa3a9a89bb04a78abce20291f9ab8fe";
    RequestParameters myRequestParameters;

    MoPubNative moPubNative;
    ViewGroup mContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.native_video_activity);
        mContainer = (ViewGroup)findViewById(R.id.adView);

        Log.e("====", "onCreate: ========");
//        ListView lv = (ListView) findViewById(R.id.lv);

//        ViewBinder mViewBinder = new ViewBinder.Builder(R.layout.native_video_item)
//                .titleId(R.id.native_ad_title)
//                .textId(R.id.native_ad_text)
//                .iconImageId(R.id.native_ad_icon_image)
//                .callToActionId(R.id.native_ad_cta)
//                .privacyInformationIconImageId(R.id.native_ad_privacy_information_icon_image)
//                .mainImageId(R.id.mediaview)
//                .build();




        final MintegralAdRenderer adRenderer = new MintegralAdRenderer(new MintegralAdRenderer.MintegralViewBinder.Builder(R.layout.native_video_item)
                .mainImageId(R.id.mediaview)
                .iconImageId(R.id.native_ad_icon_image)
                .titleId(R.id.native_ad_title)
                .textId(R.id.native_ad_text)
                .callToActionId(R.id.native_ad_cta)
                .privacyInformationIconImageId(R.id.native_ad_privacy_information_icon_image)
                .adChoicesId(R.id.mintegral_mediaview_adchoice)
                .build());

        final EnumSet<RequestParameters.NativeAdAsset> desiredAssets = EnumSet.of(
                RequestParameters.NativeAdAsset.TITLE,
                RequestParameters.NativeAdAsset.TEXT,
                // Don't pull the ICON_IMAGE
                // NativeAdAsset.ICON_IMAGE,
                RequestParameters.NativeAdAsset.MAIN_IMAGE,
                RequestParameters.NativeAdAsset.CALL_TO_ACTION_TEXT);
//
//
        myRequestParameters = new RequestParameters.Builder()
                .desiredAssets(desiredAssets)
                .build();

//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1);
//        for (int i = 0; i < 100; ++i) {
//            adapter.add("Item " + i);
//        }
//
//
//        MoPubNativeAdPositioning.MoPubServerPositioning adPositioning =
//                MoPubNativeAdPositioning.serverPositioning();



        //注释掉的是另外一种加载方式

        moPubNative = new MoPubNative(this, MY_AD_UNIT_ID, this);


        moPubNative.registerAdRenderer(adRenderer);
        moPubNative.makeRequest(myRequestParameters);

//        myAdapter.setViewBinder(mViewBinder);



//        mAdapter = new MoPubAdAdapter(this,adapter,adPositioning);
//        mAdapter.registerAdRenderer(adRenderer);
//        lv.setAdapter(mAdapter);


    }

    @Override
    protected void onResume() {

//        mAdapter.loadAds(MY_AD_UNIT_ID,myRequestParameters);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
//        mAdapter.destroy();
        super.onDestroy();
    }

    @Override
    public void onNativeLoad(NativeAd nativeAd) {


        View adWrapper = nativeAd.createAdView(MintegralNativeActivity.this,mContainer);

        nativeAd.setMoPubNativeEventListener(new NativeAd.MoPubNativeEventListener() {
            @Override
            public void onImpression(View view) {
                Log.e("","========nor====onImpression");
            }

            @Override
            public void onClick(View view) {
                Log.e("","========nor====onClick");
            }
        });
        nativeAd.renderAdView(adWrapper);
        mContainer.addView(adWrapper);
        Log.e("","========nor====LOADED");

    }

    @Override
    public void onNativeFail(NativeErrorCode errorCode) {
        Log.e("","============FAIL");
    }
}
