[![Build Status](https://travis-ci.org/asimone0/simplelogger.svg?branch=master)](https://travis-ci.org/asimone0/simplelogger)
[ ![Download](https://api.bintray.com/packages/asimone0/maven/simplelogger/images/download.svg) ](https://bintray.com/asimone0/maven/simplelogger/_latestVersion)

## simplelogger

A very simple logging library for android.

Offers ONLY debug and error logging variants, and a simple boolean flag to turn it all off.

A typical usage (kotlin):
```kotlin
SimpleLogger.shouldLog = BuildConfig.DEBUG;
SimpleLogger.log("Activity", "Something noteworthy happened")
SimpleLogger.log(this, "Error!", Exception("Something went wrong"))
```

More details:

`Logger` is a simple interface and can be customized to your needs.

By default, SimpleLogger uses the provided `AndroidLogger` class.

Note: The log tag is derived from the toString() result of the object passed in.

####

```gradle
repositories {
    jcenter()
}


dependencies {
    implementation 'click.simone:simplelogger:<version>'
}
```

