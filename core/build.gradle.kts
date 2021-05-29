plugins {
    id("com.android.library")
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
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
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
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(
        kotlin(
            module = "stdlib-jdk7",
            version = org.jetbrains.kotlin.config.KotlinCompilerVersion.VERSION
        )
    )
    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    //room
    implementation("androidx.room:room-runtime:2.3.0")
    kapt("androidx.room:room-compiler:2.3.0")
    // test
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:3.1.0")
    testImplementation("org.mockito:mockito-inline:3.1.0")
    androidTestImplementation("org.mockito:mockito-android:2.25.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    // LiveData & ViewModel
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    api("androidx.lifecycle:lifecycle-common-java8:2.3.1")
    api("androidx.lifecycle:lifecycle-compiler:2.3.1")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    api("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    api("androidx.lifecycle:lifecycle-extensions:2.2.0")
    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //stetho
    api("com.facebook.stetho:stetho:1.6.0")
    api("com.facebook.stetho:stetho-okhttp3:1.6.0")
    // okhttp
    api("com.squareup.okhttp3:logging-interceptor:4.9.1")
    // logging
    api("com.jakewharton.timber:timber:4.7.1")
    // DI
    api("com.google.dagger:hilt-android:2.35.1")
    kapt("com.google.dagger:hilt-android-compiler:2.35.1")
    api("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    //navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.3.5")
    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
}