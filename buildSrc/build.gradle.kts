buildscript {
  repositories {
    maven {
      url = uri("https://plugins.gradle.org/m2/")
    }
    mavenCentral()
  }
  dependencies {
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.30")
  }
}



apply(plugin = "org.jetbrains.kotlin.jvm")

repositories {
  jcenter()
  mavenCentral()
}

plugins {
  `kotlin-dsl`
}