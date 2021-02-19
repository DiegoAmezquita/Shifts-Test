plugins {
  id("java-library")
  id("kotlin")
  kotlin("kapt")
}

dependencies {
  implementation(Libraries.kotlinStdlib)
}