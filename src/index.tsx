import { requireNativeComponent, ViewStyle } from 'react-native';

type FacebookAudienceNetworkProps = {
  color: string;
  style: ViewStyle;
};

export const FacebookAudienceNetworkViewManager = requireNativeComponent<FacebookAudienceNetworkProps>(
  'FacebookAudienceNetworkView'
);

export default FacebookAudienceNetworkViewManager;
