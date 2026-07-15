plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.stepan_vin.coursesapp.core.network"
    compileSdk = 37

    defaultConfig {
        minSdk = 24

        buildConfigField(
            "String",
            "BASE_URL",
            "\"https://drive.usercontent.google.com/\""
        )
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:database"))
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.koin.core)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.kotlinx.coroutines.android)
}
