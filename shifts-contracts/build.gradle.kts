plugins {
  id("java-library")
  id("kotlin")
  kotlin("kapt")
}

dependencies {
  kapt(AnnotationProcessors.moshiCodegen)

  implementation(project(":core"))

  implementation(Libraries.kotlinStdlib)
  implementation(Libraries.retrofit)
  implementation(Libraries.retrofitMoshi)

  implementation(Libraries.javaInject)
}