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
        buildTypes.map {
            when (it.name) {
                "release" -> {
                    it.debuggable(false)
                    it.isMinifyEnabled = true
                    it.isShrinkResources = true
                    it.proguardFiles(
                        getDefaultProguardFile("proguard-android.txt"),
                        "proguard-rules.pro"
                    )
                }
                else -> {
                    it.isMinifyEnabled = false
                    it.debuggable(true)
                    splits {
                        this.abi.isEnable = false
                        this.density.isEnable = false
                        aaptOptions.cruncherEnabled = false
                    }
                }
            }
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
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.2.0")
    // logging
    implementation("com.jakewharton.timber:timber:4.7.1")
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