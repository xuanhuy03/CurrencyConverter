import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    kotlin("kapt")
    kotlin("plugin.parcelize")
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinAndroidKsp)
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "com.huyngo.projecthuyandroid"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.huyngo.projecthuyandroid"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val properties: Properties = Properties()
        properties.load(rootProject.file("local.properties").inputStream())

        buildConfigField("String", "EXCHANGE_RATE_KEY", "\"${properties.getProperty("EXCHANGE_RATE_API_KEY")}\"")
    }

    buildFeatures{
        buildConfig = true
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Navigation
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    // Lifecycle
    androidTestImplementation(libs.lifecycle.livedata.ktx)
    androidTestImplementation(libs.lifecycle.viewmodel.ktx)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Picasso
    implementation(libs.picasso)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.okhttp3.logging.interceptor)

    // Moshi
    implementation(libs.moshi)
    implementation(libs.moshi.converter)
    implementation(libs.moshi.kotlin)

    // Dotenv
    implementation(libs.dotenv)

    // Testing
    testImplementation(libs.core.testing)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
}
