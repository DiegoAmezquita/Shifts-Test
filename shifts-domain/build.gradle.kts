plugins {
  id("java-library")
  id("kotlin")
  kotlin("kapt")
}

dependencies {
  kapt(AnnotationProcessors.moshiCodegen)

  implementation(project(":shifts-contracts"))
  implementation(project(":core"))

  implementation(Libraries.kotlinStdlib)
  implementation(Libraries.retrofit)
  implementation(Libraries.retrofitMoshi)
  implementation(Libraries.moshi)
  implementation(Libraries.javaInject)

  testImplementation(Libraries.mockk)
  testImplementation(Libraries.kotlinTest)
  testImplementation(Libraries.kotlinTestJunit)
  testImplementation(Libraries.jUnit)
  testImplementation(Libraries.jUnitParams)
  testImplementation(Libraries.kotlinCoroutinesTest)

  testRuntimeOnly(Libraries.jUnitEngine)

}