plugins {
    id("plugin.android-common")
}

android {
    namespace = "com.blinkist.data"
}

dependencies {
    CORE
    retrofitDependencies()
    baseDependencies()
    testDependencies()
    timeDependencies()
}
