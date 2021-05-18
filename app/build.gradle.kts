plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId("co.id.kadaluarsa.gigithubuser")
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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

    api(project(":core"))
    api(project(":mainui"))
    api(project(":feature:navigation"))
    api(project(":feature:github"))
    api(project(":feature:search"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(
        kotlin(
            module = "stdlib-jdk7",
            version = org.jetbrains.kotlin.config.KotlinCompilerVersion.VERSION
        )
    )
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.2.0")
    // test
    testImplementation("junit:junit:4.12")
    testImplementation("org.mockito:mockito-core:2.23.4")
    testImplementation("org.mockito:mockito-inline:2.23.4")
    androidTestImplementation("org.mockito:mockito-android:2.15.0")
    //stetho
    implementation("com.facebook.stetho:stetho:1.6.0")
    implementation("com.facebook.stetho:stetho-okhttp3:1.6.0")
    // LiveData & ViewModel
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    // logging
    implementation("com.jakewharton.timber:timber:4.7.1")
    //room
    kapt("androidx.room:room-compiler:2.3.0")
    // DI
    implementation("com.google.dagger:hilt-android:2.35.1")
    kapt("com.google.dagger:hilt-android-compiler:2.35.1")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    //navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.3.5")


}