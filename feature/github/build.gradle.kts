plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}



android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
    buildFeatures {
        compose = true
        dataBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
    testOptions {
        unitTests.apply {
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    api(project(":mainui"))
    api(project(":core"))
    api(project(":feature:navigation"))

    implementation("androidx.recyclerview:recyclerview:1.2.0")
}