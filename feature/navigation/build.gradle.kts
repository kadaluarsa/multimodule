plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
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
    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    // test
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:3.1.0")
    testImplementation("org.mockito:mockito-inline:3.1.0")
    androidTestImplementation("org.mockito:mockito-android:2.25.0")
    // logging
    implementation("com.jakewharton.timber:timber:4.7.1")
    // DI
    implementation("com.google.dagger:hilt-android:2.35.1")
    kapt("com.google.dagger:hilt-android-compiler:2.35.1")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    //navigation
    api("androidx.navigation:navigation-fragment-ktx:2.3.5")
    api("androidx.navigation:navigation-ui-ktx:2.3.5")
    api("androidx.navigation:navigation-dynamic-features-fragment:2.3.5")
}