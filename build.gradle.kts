plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.devtools.ksp") version "1.9.24-1.0.20" apply false

    id("com.google.gms.google-services") version "4.4.2" apply false
}