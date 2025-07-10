# Native Module Setup (React Native)

## Android Setup 

### 1. Create the Native Module
#### **File:** [`android/app/src/main/java/com/<package-name>/MyModule.kt`]()

```kotlin
package com.<package-name>

import com.facebook.react.bridge.*

class MyModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    override fun getName(): String = "MyModule"

    @ReactMethod
    fun getName(name: String?, promise: Promise) {
        promise.resolve("This is Android MyModule $name")
    }
}

```

### 2. Create the React Package
#### **File:** [`android/app/src/main/java/com/<package-name>/MyPackage.kt`]()

```kotlin
package com.<package-name>

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager

class MyPackage : ReactPackage {
    override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> {
        return listOf(MyModule(reactContext))
    }

    override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
        return emptyList()
    }
}
```

### 3. Register the Package in `MainApplication.kt`
#### **File:** [`android/app/src/main/java/com/<package-name>/MainApplication.kt`]()
```kotlin
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
```

### 4. Rebuild the Project
After adding the native module, rebuild the project to apply the changes:

```sh
cd android
./gradlew clean 
cd ..
npm run android
```

## iOS Setup
### **Note** : Create all Required files from **xCode**.

### 1. Create MyModule.swift (Swift)
#### **File:** [`ios/MyModule.swift`]()

```swift
import Foundation
import AVFoundation
import React

@objc(MyModule)
class MyModule: NSObject, RCTBridgeModule{
  static func moduleName() -> String! {
    return "MyModule";
  }
  
  @objc
  func getName(_ name: String?, resolver: @escaping RCTPromiseResolveBlock, rejecter: @escaping RCTPromiseRejectBlock) {
    resolver("This is iOS MyModule \(name)")
  }
  
  @objc
  static func requiresMainQueueSetup() -> Bool {
      return true
  }
}
```

### 2. Create MyModule.m (Objective-C)
#### **File:** [`ios/MyModule.m`]()

```objc
#import <Foundation/Foundation.h>
#import "React/RCTBridgeModule.h"
#import "React/RCTEventEmitter.h"

@interface RCT_EXTERN_MODULE(MyModule, RCTEventEmitter)
RCT_EXTERN_METHOD(getName:(NSString *)name resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)
@end
```

### 3. Create the Bridging Header
#### **File:** [`ios/<YourAppName>-Bridging-Header.h`]()
```objc
#import "React/RCTBridgeModule.h"
#import "React/RCTEventEmitter.h"
```

### 4. Rebuild the Project
#### After adding the native module, clear ios/build and rebuild the project to apply the changes:

```sh
cd ios
pod install  
cd ..
npm run ios
```

## Usage - useNativeModule hook
#### Code [`src/hooks/useNativeModule.js`]()

```javascript
import { NativeModules } from 'react-native';

const { MyModule } = NativeModules; // Replace MyModule with your native module

MyModule?.getName('Hello').then(console.log) // Replace .getName('') with your Function
```