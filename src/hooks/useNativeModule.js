import { NativeModules } from 'react-native';

const { MyModule } = NativeModules; // Replace MyModule with your native module

MyModule?.getName('Hello').then(console.log) // Replace .getName('') with your Function