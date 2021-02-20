plugins {
  id("com.android.library")
  id("dagger.hilt.android.plugin")
  kotlin("android")
  kotlin("kapt")
}

android {
  compileSdkVersion(Android.compileSDK)
  buildToolsVersion = Android.buildTools

  defaultConfig {
    minSdkVersion(Android.minSDK)
    targetSdkVersion(Android.targetSDK)
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  buildFeatures {
    dataBinding = true
  }


  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}

dependencies {

  kapt(AnnotationProcessors.hilt)

  implementation(Libraries.kotlinStdlib)
  implementation(Libraries.androidXCore)
  implementation(Libraries.appCompat)
  implementation(Libraries.material)
  implementation(Libraries.constraintLayout)
  implementation(Libraries.retrofit)
  implementation(Libraries.coil)

  implementation(Libraries.hilt)

  testImplementation(Libraries.jUnit)
  testRuntimeOnly(Libraries.jUnitEngine)
  testImplementation(Libraries.jUnitParams)
}