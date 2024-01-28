plugins {
    id("plugin.android-common")
}

android {
    namespace = "com.blinkist.booklist"

}

dependencies {
    DOMAIN
    CORE
    THEME
    timeDependencies()
    composeDependencies()

}