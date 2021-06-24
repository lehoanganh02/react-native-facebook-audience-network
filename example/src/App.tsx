import * as React from 'react';

import { StyleSheet, View } from 'react-native';
import { NativeSquareAd, NativeListAd } from 'react-native-facebook-audience-network';

export default function App() {
  return (
    <View style={styles.container}>
      <NativeSquareAd placementId="IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID" style={styles.box} />
      <NativeListAd placementId="IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID" style={styles.box} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
  },
  box: {
    width: "100%",
  //  height: 500,
  },
});
