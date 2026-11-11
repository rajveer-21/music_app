plugins {
    id("com.android.application")

    id("org.jetbrains.kotlin.android")

    id("org.jetbrains.kotlin.plugin.compose")

    id("com.google.devtools.ksp")
}

android {

    namespace = "com.example.pulsemusic"

    compileSdk = 36

    defaultConfig {

        applicationId = "com.example.pulsemusic"

        minSdk = 26

        targetSdk = 35

        versionCode = 1

        versionName = "1.0"
    }

    compileOptions {

        sourceCompatibility = JavaVersion.VERSION_17

        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {

        jvmTarget = "17"
    }

    buildFeatures {

        compose = true
    }
}

dependencies {

    // Compose BOM

    implementation(
        platform("androidx.compose:compose-bom:2025.09.00")
    )

    // Android

    implementation("androidx.activity:activity-compose:1.11.0")

    // Compose UI

    implementation("androidx.compose.ui:ui")

    implementation("androidx.compose.ui:ui-tooling-preview")

    implementation("androidx.compose.material3:material3")

    implementation("androidx.compose.material:material-icons-extended")

    debugImplementation("androidx.compose.ui:ui-tooling")

    // Lifecycle and ViewModel

    implementation(
        "androidx.lifecycle:lifecycle-runtime-compose:2.9.4"
    )

    implementation(
        "androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4"
    )

    // Navigation

    implementation(
        "androidx.navigation:navigation-compose:2.9.4"
    )

    // Media3

    implementation(
        "androidx.media3:media3-exoplayer:1.8.0"
    )

    implementation(
        "androidx.media3:media3-session:1.8.0"
    )

    implementation(
        "androidx.media3:media3-ui:1.8.0"
    )

    // Required for MediaController await()

    implementation(
        "org.jetbrains.kotlinx:kotlinx-coroutines-guava:1.10.2"
    )

    // Room

    implementation(
        "androidx.room:room-runtime:2.8.2"
    )

    implementation(
        "androidx.room:room-ktx:2.8.2"
    )

    ksp(
        "androidx.room:room-compiler:2.8.2"
    )

    // DataStore

    implementation(
        "androidx.datastore:datastore-preferences:1.1.7"
    )

    // Coroutines

    implementation(
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2"
    )
}