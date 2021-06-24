package com.reactnativefacebookaudiencenetwork;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.ArrayList;
import java.util.List;

public class FacebookAudienceNetworkNativeAdView extends LinearLayout {
  private final String TAG = "Ajith";
  private ThemedReactContext context;
  private NativeAdLayout nativeAdLayout;
  private LinearLayout adView;
  private NativeAd nativeAd;
  private RCTEventEmitter mEventEmitter;
  public String adListnerName;
  public String adPlacementId;

  public FacebookAudienceNetworkNativeAdView(ThemedReactContext themedReactContext) {
    super(themedReactContext);
    context = themedReactContext;
    mEventEmitter = context.getJSModule(RCTEventEmitter.class);
    inflate(context, R.layout.native_ad_card, this);
    AudienceNetworkAds.initialize(context);
  }

  public void addListnerName(String lintnerName){
    adListnerName = lintnerName;
  }

  public void loadNativeAd(String placementId) {
    adPlacementId = placementId;
    nativeAd = new NativeAd(context, placementId);
    NativeAdListener nativeAdListener = new NativeAdListener() {
      @Override
      public void onMediaDownloaded(Ad ad) {
        // Native ad finished downloading all assets
        Log.e(TAG, "Native ad finished downloading all assets.");
      }

      @Override
      public void onError(Ad ad, AdError adError) {
        context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
          .emit(adListnerName.concat("-onAdFailedToLoad"), adError.toString());
        // Native ad failed to load
        Log.e(TAG, "Native ad failed to load: " + adError.getErrorMessage());
      }

      @Override
      public void onAdLoaded(Ad ad) {
        Log.d(TAG, "Native ad is loaded and ready to be displayed!");
        // Native ad is loaded and ready to be displayed
        if (nativeAd == null || nativeAd != ad) {
          return;
        }
        // Inflate Native Ad into Container
        inflateAd(nativeAd);

      }

      @Override
      public void onAdClicked(Ad ad) {
        // Native ad clicked
        Log.d(TAG, "Native ad clicked!");
      }

      @Override
      public void onLoggingImpression(Ad ad) {
        // Native ad impression
        Log.d(TAG, "Native ad impression logged!");
      }
    };

    // Request an ad
    nativeAd.loadAd(
      nativeAd.buildLoadAdConfig()
        .withAdListener(nativeAdListener)
        .build());

  }


  private void inflateAd(NativeAd nativeAd) {

    nativeAd.unregisterView();

    // Add the Ad view into the ad container.
    nativeAdLayout = findViewById(R.id.native_ad_container);
    LayoutInflater inflater = LayoutInflater.from(context);
    // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
    adView = (LinearLayout) inflater.inflate(R.layout.native_ad_layout, nativeAdLayout, false);
    nativeAdLayout.addView(adView);

    // Add the AdOptionsView
    LinearLayout adChoicesContainer = findViewById(R.id.ad_choices_container);
    AdOptionsView adOptionsView = new AdOptionsView(context, nativeAd, nativeAdLayout);
    adChoicesContainer.removeAllViews();
    adChoicesContainer.addView(adOptionsView, 0);

    // Create native UI using the ad metadata.
    MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
    TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
    MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
    TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
    TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
    TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
    Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

    // Set the Text.
    nativeAdTitle.setText(nativeAd.getAdvertiserName());
    nativeAdBody.setText(nativeAd.getAdBodyText());
    nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
    nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
    nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
    sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

    // Create a list of clickable views
    List<View> clickableViews = new ArrayList<>();
    clickableViews.add(nativeAdTitle);
    clickableViews.add(nativeAdCallToAction);

    // Register the Title and CTA button to listen for clicks.
    nativeAd.registerViewForInteraction(
      adView, nativeAdMedia, nativeAdIcon, clickableViews);

    context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit(adListnerName.concat("-onAdLoaded"), adPlacementId);
  }


}
