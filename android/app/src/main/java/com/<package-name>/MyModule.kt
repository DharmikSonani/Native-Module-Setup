package com.<package-name>

import com.facebook.react.bridge.*

class MyModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    override fun getName(): String = "MyModule"

    @ReactMethod
    fun getName(name: String?, promise: Promise) {
        promise.resolve("This is Android MyModule $name")
    }
}
