# react-native-facebook-audience-network

Facebook Audience SDK integration for React Native, available on iOS and Android. Features native, interstitial and banner ads. 

## Example

![alt text](https://raw.githubusercontent.com/ajith-ab/react-native-facebook-audience-network/main/docs/nativeAd.png)



## Installation (Currently Android only)

```sh
npm install react-native-facebook-audience-network
```

## Usage

```js
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

```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
