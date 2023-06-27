package com.rams.family.tracker

import androidx.multidex.MultiDexApplication
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.rams.family.tracker.model.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

val auth by lazy {
    Firebase.auth
}
val db by lazy {
    Firebase.firestore
}
val user: FirebaseUser? get() = auth.currentUser

class FamilyTrackerApplication: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@FamilyTrackerApplication)
            androidLogger()
            modules(AppModule().module)
        }

        user?.let { fireUser ->

        }

    }

}