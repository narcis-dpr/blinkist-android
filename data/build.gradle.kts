plugins {
    id("plugin.android-common")
}

android {
    namespace = "com.blinkist.data"
}

dependencies {
//    testImplementation("junit:junit:4.12")
    CORE
    retrofitDependencies()
    baseDependencies()
    testDependencies()
    timeDependencies()
    roomDependencies()
}
