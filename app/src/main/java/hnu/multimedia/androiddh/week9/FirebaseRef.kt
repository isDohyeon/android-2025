package hnu.multimedia.androiddh.week9

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRef {

    companion object {
        private val database = Firebase.database
        val favoriteColors = database.getReference("favoriteColors")
        val fcmToken = database.getReference("fcmToken")
        val androidFCM = database.getReference("fcmToken/android")
        val galaxyFCM = database.getReference("fcmToken/galaxy")
    }
}