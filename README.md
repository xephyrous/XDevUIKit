![XDevUIKit Logo](https://github.com/xephyrous/XDevUIKit/blob/master/src/main/resources/XDevUIKit_large.png)

---

#### Overview
- [What is XDevUIKit?](#what-is-xdevuikit)
- [Installation](#installation)
- [Features](#features)
- [Examples](https://github.com/xephyrous/XDevUIKit/tree/master/src/main/kotlin/xdevuikit/core/examples)

---

### What is XDevUIKit?

XDevUIKit, referred to colloquially as XGooey, is Xephyrous's in-house UI development library for Compose Multiplatform. XGooey contains custom components, 
layouts, and utilities designed to increase the speed of app development, while keeping a nice look and feel with plenty of customizability.

---

### Features

XDevUIKit's main improvements are in its custom components designed for faster and easier front-end development. These components were designed to be good looking, 
light, and easy to use, all while providing smooth user experience. If you're interested in extending the functionality of any of XGooey's features, look to our
[documentation]() and [framework design]() resources. 

- _For more in depth examples on any of the following features, each section title will link to its example file! Alternatively, there's a link to the examples folder in the **Overview**_

### ❏  [FlexBox](https://github.com/xephyrous/XDevUIKit/blob/master/src/main/kotlin/xdevuikit/core/examples/FlexBoxExample.kt)
- The **FlexBox** is a modular container with built-in animation capabilities. It offers ```flex()``` for animating size, along with ```float()``` and ```snap()``` for animating position.
- **FlexBox**es are extremely simple to work with, as their functions can be called directly from **_anywhere_** within their content sections:

```kotlin
var textStr by remember { mutableStateOf("Hello...") }

FlexBox {
  Button(
    onClick = {
      textStr = "Hello, XGooey!"
      flex(100.dp, 50.dp, 500, LinearEasing)
    }
  ) {
    Text(textStr)
  }
}
```

- **FlexBox**es also provide asynchronous versions of their animation functions, which can be chained together to create more complex animation sequences:

```kotlin
var textStr by remember { mutableStateOf("Hello...") }

FlexBox {
  Button(
    onClick = {
      textStr = "Hello, XGooey!"
      globalScope.launch {
        flexAsync(100.dp, 50.dp, 500, LinearEasing)
          .wait(1000)
          .floatAsync(50.dp, 50.dp, 500, LinearEasing)
      }
    }
  ) {
    Text(textStr)
  }
}
```

- The last, and arguably most useful, feature of **FlexBox**es is their ability to revert state using ```revert()```, ```revertFlex()```, and ```revertFloat()```.
- These functions return the flexbox to its last state (position, size, or both), using the same easing and durations, with a single call! (And they even work asynchronously)

```kotlin
var textStr by remember { mutableStateOf("Hello...") }

FlexBox {
  Button(
    onClick = {
      textStr = "Hello, XGooey!"
      globalScope.launch {
        flexAsync(100.dp, 50.dp, 500, LinearEasing)
          .wait(1000)
          .floatAsync(50.dp, 50.dp, 500, LinearEasing)
          .revertAsync()
      }
    }
  ) {
    Text(textStr)
  }
}
```

---

### Installation

XGooey's main repository is setup as a Compose Desktop project, meaning once you clone it, you can get straight to developing!
Otherwise there are dedicated repositories for each specific target ([IOS](), [Android](), [Web]()), along with a repository
containing the library itself without any bloat or examples ([XGooey]()). Further down the road, there will be a web-based project
builder for mixing targets and settings, similar to the [Compose Multiplatform WIzard](https://kmp.jetbrains.com/).
