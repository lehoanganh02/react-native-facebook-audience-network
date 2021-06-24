import React, { FC, useEffect, useState } from 'react';
import { DeviceEventEmitter, Platform, requireNativeComponent, ViewStyle } from 'react-native';

type FacebookAudienceNetworkProps = {
  style?: ViewStyle;
  onAdLoaded?: () => void,
  onAdFailedToLoad?: () => void,
  onAdClosed?: () => void
  placementId: string
};

const FacebookAudienceNetworkViewManager = requireNativeComponent<any>(
  'FacebookAudienceNetworkListView'
);

const NativeAd: FC<FacebookAudienceNetworkProps> = (props) => {
  const isIos: boolean = Platform.OS === "ios";
    const [adLoaded, setAdLoaded] = useState<boolean>(false);
    const [listnerName, setListnerName] = useState<string>(new Date().getTime().toString() + 'facebookAds-' + Math.random());
    useEffect(()=>{
      DeviceEventEmitter.addListener(`${listnerName}-onAdLoaded`, ()=>{
        if(!adLoaded){
         setTimeout(() => {
          setAdLoaded(true);
          if(props.onAdLoaded instanceof  Function)props.onAdLoaded()
         }, 500);
        }
      });
      DeviceEventEmitter.addListener(`${listnerName}-onAdFailedToLoad`, ()=>{
        if(props.onAdFailedToLoad instanceof  Function)props.onAdFailedToLoad()
      }); 
      DeviceEventEmitter.addListener(`${listnerName}-onAdClosed`, ()=>{
        if(props.onAdClosed instanceof  Function)props.onAdClosed()
      }); 

      return () => {
          DeviceEventEmitter.removeListener(`${listnerName}-AdLoaded`, ()=>{
              setListnerName("");
          });
          DeviceEventEmitter.removeListener(`${listnerName}-onAdFailedToLoad`, ()=>{
          }); 
          DeviceEventEmitter.removeListener(`${listnerName}-onAdClosed`, ()=>{
          }); 
      }
  },[])


  if(isIos)return <></>;
  return(
        <FacebookAudienceNetworkViewManager 
              listnerName={listnerName}
              style={{ ...props.style, height: adLoaded ? props.style?.height ? props.style?.height : 70 : 0 }}
              placementId={props.placementId}
        />
  )

}



export default NativeAd;
