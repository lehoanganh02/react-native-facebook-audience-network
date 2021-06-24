package com.reactnativefacebookaudiencenetwork;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

public class FacebookAudienceNetworkListViewManager extends SimpleViewManager<FacebookAudienceNetworkNativeListAdView> {

  @NonNull
  @Override
  public String getName() {
    return "FacebookAudienceNetworkListView";
  }

  @NonNull
  @Override
  protected FacebookAudienceNetworkNativeListAdView createViewInstance(@NonNull ThemedReactContext reactContext) {
    return new FacebookAudienceNetworkNativeListAdView(reactContext);
  }

  @ReactProp(name = "listnerName")
  public void setListner(FacebookAudienceNetworkNativeListAdView facebookAudienceNetworkNativeAdView,  String listner) {
    facebookAudienceNetworkNativeAdView.addListnerName(listner);
  }

  @ReactProp(name = "placementId")
  public void setConfig(FacebookAudienceNetworkNativeListAdView facebookAudienceNetworkNativeAdViewtiveAdView, String placementId) {
    facebookAudienceNetworkNativeAdViewtiveAdView.loadNativeAd(placementId);
  }


}
