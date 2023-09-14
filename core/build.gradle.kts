import Dependencies.Squareup
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id(Plugins.COMMON_LIBRARY)
}
android {
    namespace = "com.bbrustol.core"

    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com/\"")
    }

    dependencies {
        implementation (Squareup.RETROFIT)
        implementation (Squareup.OKHTTP3)

        testImplementation(kotlin("test"))
    }
}
