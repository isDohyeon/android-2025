package hnu.multimedia.androiddh.week14

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hnu.multimedia.androiddh.databinding.ActivityMain14Binding

class MainActivity14 : AppCompatActivity() {

    private val binding by lazy { ActivityMain14Binding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        NotificationUtil.requestPermissions(this)

        binding.buttonSend.setOnClickListener {
            val message = binding.editTextMessage.text.toString()
            MyFirebaseMessagingSender().sendFCM("", "메시지가 도착했습니다.", message)
        }
    }
}