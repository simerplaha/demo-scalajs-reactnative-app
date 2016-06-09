##What ?

A small Scala.js React Native app that is build on chandu0101's [scalajs-react-native](https://github.com/chandu0101/scalajs-react-native)
with some changes.

1. Updated libraries
2. Updated APIs
3. Updated

This project also requires this fork of scalajs-react which implements React Native APIS [simerplaha/scalajs-react](https://github.com/simerplaha/scalajs-react)

1. Clone the above repository
2. sbt publish-local

## Running example

![MyApp](examples/images/myApp.gif)

## Run

```scala
sbt ~fullOptIOS

// new terminal
cd examples
npm install
npm run start
```

## Runtime error

I'm getting the following runtime error after upgrading the React-native version to latest. This seems to be a JS
issue which I will try to work on soon. [Stackoverflow question](http://stackoverflow.com/questions/35474610/unable-to-execute-js-call-fbbatchedbridge-is-undefined)

```
Unable to execute JS class: __fbBatchedBridge is undefined
```



