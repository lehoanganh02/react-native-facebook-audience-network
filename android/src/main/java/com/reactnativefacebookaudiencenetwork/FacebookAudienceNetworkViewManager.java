package com.reactnativefacebookaudiencenetwork;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

public class FacebookAudienceNetworkViewManager extends SimpleViewManager<FacebookAudienceNetworkNativeAdView> {

  @NonNull
  @Override
  public String getName() {
    return "FacebookAudienceNetworkView";
  }

  @NonNull
  @Override
  protected FacebookAudienceNetworkNativeAdView createViewInstance(@NonNull ThemedReactContext reactContext) {
    return new FacebookAudienceNetworkNativeAdView(reactContext);
  }

  @ReactProp(name = "listnerName")
  public void setListner(FacebookAudienceNetworkNativeAdView facebookAudienceNetworkNativeAdView,  String listner) {
    facebookAudienceNetworkNativeAdView.addListnerName(listner);
  }

  @ReactProp(name = "placementId")
  public void setConfig(FacebookAudienceNetworkNativeAdView facebookAudienceNetworkNativeAdViewtiveAdView, String placementId) {
    facebookAudienceNetworkNativeAdViewtiveAdView.loadNativeAd(placementId);
  }


}
