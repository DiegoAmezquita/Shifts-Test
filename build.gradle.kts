import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
  repositories {
    google()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
  }
  dependencies {
    classpath("com.android.tools.build:gradle:4.1.2")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
  }
}
allprojects {
  repositories {
    google()
    mavenCentral()
  }
}

subprojects {
  tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = Versions.jvmTarget
  }
}

tasks.register("clean", Delete::class.java) {
  delete(rootProject.buildDir)
}