object Versions {
  const val kotlinPlugin = "1.4.0"

  const val kotlin = "1.4.30"
  const val jvmTarget = "1.8"
  const val hilt = "2.32-alpha"
  const val retrofit = "2.9.0"
  const val moshi = "1.11.0"
}

object Android {
  const val compileSDK = 30
  const val buildTools = "30.0.3"
  const val minSDK = 16
  const val targetSDK = 30
}

object Libraries {
  const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
  const val multidex = "androidx.multidex:multidex:2.0.1"
  const val androidXCore = "androidx.core:core-ktx:1.3.2"
  const val appCompat = "androidx.appcompat:appcompat:1.2.0"
  const val material = "com.google.android.material:material:1.3.0"
  const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
  const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
  const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
  const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
  const val androidXFragments = "androidx.fragment:fragment-ktx:1.3.0"
  const val androidXViewModels ="androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0"
  const val androidXLiveData ="androidx.lifecycle:lifecycle-livedata-ktx:2.3.0"

  const val coil = "io.coil-kt:coil:1.1.1"

  const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"

  const val javaInject = "javax.inject:javax.inject:1"

  const val jUnit = "org.junit.jupiter:junit-jupiter-api:5.7.1"
  const val jUnitEngine = "org.junit.jupiter:junit-jupiter-engine:5.7.1"
  const val jUnitParams = "org.junit.jupiter:junit-jupiter-params:5.7.1"

  const val androidXJUnit = "androidx.test.ext:junit:1.1.2"
  const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
}

object AnnotationProcessors {
  const val hilt = "com.google.dagger:hilt-compiler:${Versions.hilt}"
  const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
}