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
