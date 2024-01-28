import Version.AndroidXTestVersion
import Version.AppCompat
import Version.ConstraintLayoutCompose
import Version.CoreKtx
import Version.EspressoCore
import Version.GsonVersion
import Version.GuavaAndroid
import Version.HiltAndroidVersion
import Version.HiltNavigationCompose
import Version.JunitExtKtx
import Version.LifecycleRunTimeKtx
import Version.NavigationCompose
import Version.SplashScreenApi
import Version.TestRunnerVersion
import Version.ThreeTenVersion
import Version.TruthExt
import Version.coroutineTestVersion
import Version.mockWebserverVersion
import Version.okHttpVersion
import Version.retrofitRxVersion
import Version.retrofitVersion
import Version.roomVersion

object Version {
    const val CoreKtx = "1.9.0"
    const val AppCompat = "1.6.1"
    const val LifecycleRunTimeKtx = "2.3.1"
    const val SplashScreenApi = "1.0.1"
    const val GsonVersion = "2.10.1"
    const val GuavaAndroid = "31.0.1-android"
    const val HiltNavigationCompose = "1.0.0"
    const val HiltAndroidVersion = "2.44"
    const val ComposeBom = "2023.01.00"
    const val ConstraintLayoutCompose = "1.0.1"
    const val NavigationCompose = "2.5.3"
    const val Coil = "2.2.2"
    const val AndroidXTestVersion = "1.5.0"
    const val EspressoCore = "3.5.1"
    const val TestRunnerVersion = "1.5.2"
    const val JunitExtKtx = "1.1.5"
    const val Accompanist = "0.28.0"
    const val TruthExt = "1.5.0"
    const val mockWebserverVersion = "4.11.0"
    const val retrofitVersion = "2.9.0"
    const val okHttpVersion = "5.0.0-alpha.2"
    const val retrofitRxVersion = "2.6.2"
    const val ThreeTenVersion = "1.6.8"
    const val coroutineTestVersion = "1.7.3"
    const val roomVersion = "2.4.1"
}

object Libraries {
    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:$CoreKtx"
        const val appCompat = "androidx.appcompat:appcompat:$AppCompat"
        const val lifecycleRunTimeKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:$LifecycleRunTimeKtx"
        const val splashScreen = "androidx.core:core-splashscreen:$SplashScreenApi"
    }

    object Google {
        const val gson = "com.google.code.gson:gson:$GsonVersion"
        const val guava = "com.google.guava:guava:$GuavaAndroid"
    }
    object Retrofit {
        const val retrofit2 = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
        const val okhttp3Interceptor = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
        const val retrofitRx = "com.squareup.retrofit2:adapter-rxjava2:$retrofitRxVersion"
    }

    object Hilt {
        const val hiltAndroid = "com.google.dagger:hilt-android:$HiltAndroidVersion"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$HiltAndroidVersion"
        const val hiltNavigationCompse =
            "androidx.hilt:hilt-navigation-compose:$HiltNavigationCompose"
    }

    object Room {
        const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
        const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    }
    object Compose {
        const val composeBom = "androidx.compose:compose-bom:${Version.ComposeBom}"
        const val composeUi = "androidx.compose.ui:ui"
        const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val composeToolingUi = "androidx.compose.ui:ui-tooling:1.0.0-beta09"
        const val composeMaterial3 = "androidx.compose.material3:material3"
        const val composeFoundation = "androidx.compose.foundation:foundation"
        const val composeRuntime = "androidx.compose.runtime:runtime"
        const val composeActivity = "androidx.activity:activity-compose:1.6.1"
        const val composeUiUtil = "androidx.compose.ui:ui-util"
        const val materialIcons = "androidx.compose.material:material-icons-extended"
        const val constraintLayoutCompose =
            "androidx.constraintlayout:constraintlayout-compose:$ConstraintLayoutCompose"
    }

    object Naviagtion {
        const val navigationCompose = "androidx.navigation:navigation-compose:$NavigationCompose"
    }

    object Coil {
        const val coilCompose = "io.coil-kt:coil-compose:${Version.Coil}"
        const val coilVideo = "io.coil-kt:coil-video:${Version.Coil}"
    }
    object Test {
        const val testCoreKtx = "androidx.test:core-ktx:$AndroidXTestVersion"
        const val espressorCore = "androidx.test.espresso:espresso-core:$EspressoCore"
        const val junitExtKtx = "androidx.test.ext:junit-ktx:$JunitExtKtx"
        const val truthExt = "androidx.test.ext:truth:$TruthExt"
        const val runner = "androidx.test:runner:$TestRunnerVersion"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$mockWebserverVersion"
        const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineTestVersion"
    }
    object Time {
        const val ThreeTen = "org.threeten:threetenbp:$ThreeTenVersion"
    }
    object Accompanist {
        const val pager =
            "com.google.accompanist:accompanist-pager:0.29.2-rc"
        const val swiperefresh =
            "com.google.accompanist:accompanist-swiperefresh:${Version.Accompanist}"
        const val indicators =
            "com.google.accompanist:accompanist-pager-indicators:${Version.Accompanist}"
        const val systemuicontroller =
            "com.google.accompanist:accompanist-systemuicontroller:${Version.Accompanist}"
        const val webView = "com.google.accompanist:accompanist-webview:${Version.Accompanist}"
        const val navigationMaterial =
            "com.google.accompanist:accompanist-navigation-material:${Version.Accompanist}"
        const val navigationAnimation =
            "com.google.accompanist:accompanist-navigation-animation:${Version.Accompanist}"
        const val permission =
            "com.google.accompanist:accompanist-permissions:${Version.Accompanist}"
        const val insets =
            "com.google.accompanist:accompanist-insets:${Version.Accompanist}"
    }
}
