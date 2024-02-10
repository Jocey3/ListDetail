plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "dev.jocey.listdetail"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.jocey.listdetail"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        dataBinding = true
        buildConfig = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    dataBinding {
        enable = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.ads:mediation-test-suite:3.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":common"))

    val lifecycle_version = "2.6.2"
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    // Fragment
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    //RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    //Glide
    implementation("com.github.bumptech.glide:glide:4.15.1")
    //Dagger
    implementation("com.google.dagger:dagger:2.50")
    implementation("com.google.dagger:dagger-android:2.29.1")
    implementation("com.google.dagger:dagger-android-support:2.29.1")
    kapt("com.google.dagger:dagger-compiler:2.50")
    kapt("com.google.dagger:dagger-android-processor:2.29.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
}
