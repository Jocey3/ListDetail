plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlin-kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
dependencies {
    //Dagger
    api("com.google.dagger:dagger:2.50")
    api("com.google.dagger:dagger-android:2.29.1")
    api("com.google.dagger:dagger-android-support:2.29.1")
    kapt("com.google.dagger:dagger-compiler:2.50")
    kapt("com.google.dagger:dagger-android-processor:2.29.1")

    //Coroutines
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
}