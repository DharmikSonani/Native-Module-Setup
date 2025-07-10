package com.<package-name>

import com.<package-name>.MyPackage // Import Package for MyPackage (Required)

class MainApplication : Application(), ReactApplication {
  override val reactNativeHost: ReactNativeHost =
      object : DefaultReactNativeHost(this) {
        override fun getPackages(): List<ReactPackage> =
            PackageList(this).packages.apply {
              add(MyPackage()) // Required setup for connecting MyPackage with React Native (Required)
            }
    }
}
