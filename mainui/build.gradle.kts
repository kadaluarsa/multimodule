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
                kotlinOptions.freeCompilerArgs = listOf(
                    *kotlinOptions.freeCompilerArgs.toTypedArray(),
                    "-Xallow-jvm-ir-dependencies",
                    "-Xskip-prerelease-check")
                useIR = true
            }
        }
        buildFeatures {
            compose = true
            dataBinding = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.0.0-beta01"
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
        api(project(":feature:navigation"))

        api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
        api(
            kotlin(
                module = "stdlib-jdk7",
                version = org.jetbrains.kotlin.config.KotlinCompilerVersion.VERSION
            )
        )
        api("androidx.appcompat:appcompat:1.3.0")
        api("com.google.android.material:material:1.3.0")
        api("androidx.constraintlayout:constraintlayout:2.0.4")
        androidTestImplementation("androidx.test.ext:junit:1.1.2")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
        api("androidx.legacy:legacy-support-v4:1.0.0")
        //glide
        api("com.github.bumptech.glide:glide:4.12.0")
        kapt("com.github.bumptech.glide:compiler:4.12.0")
       //jetpack compose
        api("androidx.compose.ui:ui:1.0.0-beta07")
        api("androidx.compose.ui:ui-tooling:1.0.0-beta07")
        api("androidx.compose.foundation:foundation:1.0.0-beta07")
        api("androidx.compose.material:material:1.0.0-beta07")
        api("androidx.compose.material:material-icons-core:1.0.0-beta07")
        api("androidx.compose.material:material-icons-extended:1.0.0-beta07")
        api("androidx.activity:activity-compose:1.3.0-alpha08")
        api("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha05")
        api("androidx.compose.runtime:runtime-livedata:1.0.0-beta07")
        api("androidx.compose.runtime:runtime-rxjava2:1.0.0-beta07")
        androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.0-beta07")
    }
}