
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.annotationProcessor(dependencyNotation: Any): Dependency? =
    add("annotationProcessor", dependencyNotation)

fun DependencyHandler.baseDependencies() {
    implementation(Libraries.AndroidX.appCompat)
    implementation(Libraries.AndroidX.coreKtx)
    implementation(Libraries.AndroidX.lifecycleRunTimeKtx)
    implementation(Libraries.AndroidX.splashScreen)
    implementation(Libraries.Google.gson)
    implementation(Libraries.Hilt.hiltAndroid)
    kapt(Libraries.Hilt.hiltCompiler)
    implementation(Libraries.Google.guava)
}
fun DependencyHandler.retrofitDependencies() {
    implementation(Libraries.Retrofit.retrofit2)
    implementation(Libraries.Retrofit.gsonConverter)
    implementation(Libraries.Retrofit.okHttp)
    implementation(Libraries.Retrofit.okhttp3Interceptor)
    implementation(Libraries.Retrofit.retrofitRx)
}
fun DependencyHandler.roomDependencies() {
    implementation(Libraries.Room.roomRuntime)
    kapt(Libraries.Room.roomCompiler)
    implementation(Libraries.Room.roomKtx)
}
fun DependencyHandler.accompanistDependencies() {
    implementation(Libraries.Accompanist.pager)
    implementation(Libraries.Accompanist.swiperefresh)
    implementation(Libraries.Accompanist.webView)
    implementation(Libraries.Accompanist.indicators)
    implementation(Libraries.Accompanist.systemuicontroller)
    implementation(Libraries.Accompanist.navigationMaterial)
    implementation(Libraries.Accompanist.navigationAnimation)
    implementation(Libraries.Accompanist.permission)
    implementation(Libraries.Accompanist.insets)
}

fun DependencyHandler.composeDependencies() {
    implementation(platform(Libraries.Compose.composeBom))
    implementation(Libraries.Compose.composeActivity)
    implementation(Libraries.Compose.composeUi)
    implementation(Libraries.Compose.composeUiToolingPreview)
    implementation(Libraries.Compose.composeToolingUi)
    implementation(Libraries.Compose.composeUiUtil)
    implementation(Libraries.Compose.materialIcons)
    implementation(Libraries.Compose.composeFoundation)
    implementation(Libraries.Compose.composeRuntime)
    implementation(Libraries.Compose.composeMaterial3)

    // navgation
    implementation(Libraries.Naviagtion.navigationCompose)

    // coil
    implementation(Libraries.Coil.coilCompose)
    implementation(Libraries.Coil.coilVideo)

    // hilt navigation
    implementation(Libraries.Hilt.hiltNavigationCompse)

    // accompanist
    accompanistDependencies()

    // constraint layout
    implementation(Libraries.Compose.constraintLayoutCompose)
}

fun DependencyHandler.testDependencies() {
    androidTestImplementation(Libraries.Test.testCoreKtx)
    androidTestImplementation(Libraries.Test.espressorCore)
    androidTestImplementation(Libraries.Test.runner)
    androidTestImplementation(Libraries.Test.junitExtKtx)
    androidTestImplementation(Libraries.Test.truthExt)
    testImplementation(Libraries.Test.mockWebServer)
    testImplementation(Libraries.Test.coroutineTest)
    testImplementation(Libraries.Test.junit4)
    androidTestImplementation(Libraries.Test.junit)
    testImplementation(Libraries.Test.commonJunit)
    testImplementation(Libraries.Test.mockK)
    testImplementation(Libraries.Test.turbine)
    testImplementation(Libraries.Test.nhaarman)
}

fun DependencyHandler.timeDependencies() {
    implementation(Libraries.Time.ThreeTen)
}

fun DependencyHandler.moduleDependencies() {
    DATA
    CORE
    DOMAIN
    THEME
    FEATURE_BOOK_LIST
}

val DependencyHandler.DATA
    get() = implementation(project(mapOf("path" to ":data")))

val DependencyHandler.CORE
    get() = implementation(project(mapOf("path" to ":core")))

val DependencyHandler.DOMAIN
    get() = implementation(project(mapOf("path" to ":domain")))

val DependencyHandler.THEME
    get() = implementation(project(mapOf("path" to ":theme")))

val DependencyHandler.FEATURE_BOOK_LIST
    get() = implementation(project(mapOf("path" to ":feature:bookList")))

