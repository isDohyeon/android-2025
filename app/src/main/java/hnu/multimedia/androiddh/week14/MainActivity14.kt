package hnu.multimedia.androiddh.week14

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import hnu.multimedia.androiddh.databinding.ActivityMain14Binding
import hnu.multimedia.androiddh.week9.FirebaseRef

class MainActivity14 : AppCompatActivity() {

    private val binding by lazy { ActivityMain14Binding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        NotificationUtil.requestPermissions(this)

        binding.buttonSendEmulator.setOnClickListener {
            val message = binding.editTextMessage.text.toString()
            val fcmRef = FirebaseRef.androidFCM
            sendMessage(fcmRef, message)

        }

        binding.buttonSendGalaxy.setOnClickListener {
            val message = binding.editTextMessage.text.toString()
            val fcmRef = FirebaseRef.galaxyFCM
            sendMessage(fcmRef, message)
        }
    }

    private fun sendMessage(fcmRef: DatabaseReference, message: String) {
        fcmRef.get().addOnSuccessListener { snapshot ->
            for (child in snapshot.children) {
                val fcmToken = child.getValue(String::class.java)
                fcmToken?.let {
                    MyFirebaseMessagingSender().sendFCM(fcmToken, "메시지가 도착했습니다", message)
                }
            }
        }
    }
}