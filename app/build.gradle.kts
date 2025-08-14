plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.hospitalapp.samadnursinghome"
    compileSdk = 36


    defaultConfig {
        applicationId = "com.hospitalapp.samadnursinghome"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.mediation.test.suite)
    
    // Navigation Components
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    
    // ViewPager2 and Fragments
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    
    // Lottie Animations
    implementation("com.airbnb.android:lottie:6.2.0")
    
    // Blur Effect Library
    implementation("com.github.Dimezis:BlurView:version-2.0.3")
    
    // SharedPreferences (Encrypted)
    implementation("androidx.security:security-crypto:1.1.0-alpha06")
    
    // Date Picker
    implementation("com.google.android.material:material:1.11.0")
    
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.google.android.flexbox:flexbox:3.0.0")
    implementation("androidx.core:core-ktx:1.10.1")


}