package hnu.multimedia.androiddh.week14

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        Log.d("MyFirebaseMessagingService", "onNewToken: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        message.notification?.let {
            Log.d("MyFirebaseMessagingService", "onMessageReceived: ${it.title}")
            NotificationUtil.createNotification(this, it.title ?: "No Title", it.body ?: "No Body")
        }
    }
}