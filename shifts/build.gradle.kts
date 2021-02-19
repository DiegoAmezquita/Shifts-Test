plugins {
  id("com.android.application")
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
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}

dependencies {

  implementation(Libraries.kotlinStdlib)
  implementation(Libraries.androidXCore)
  implementation(Libraries.appCompat)
  implementation(Libraries.material)
  implementation(Libraries.constraintLayout)
  implementation(Libraries.retrofit)

  testImplementation(Libraries.jUnit)
  testRuntimeOnly(Libraries.jUnitEngine)
  testImplementation(Libraries.jUnitParams)
}