@file:Suppress("OldTargetApi", "GradleDependency")

plugins {
    alias(libs.plugins.application)
}

val bundleId: String by project

android {
    namespace = bundleId
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = bundleId
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 5
        versionName = "1.0.5"
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    implementation(project(":switchbutton"))

    implementation(libs.appcompat)
    implementation(libs.recyclerview)
}
