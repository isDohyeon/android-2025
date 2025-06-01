package hnu.multimedia.androiddh.week13

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import hnu.multimedia.androiddh.databinding.ActivityMain13Binding

class MainActivity13 : AppCompatActivity() {

    private val binding by lazy { ActivityMain13Binding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        NotificationUtil.requestPermissions(this)

        // www.hannam.ac.kr
        binding.buttonMakeNotification.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val message = binding.editTextMessage.text.toString()
            val url = binding.editTextUrl.text.toString()
            val importance = binding.editTextPriority.text.toString().toInt()
            if (importance < 0 || importance > 4) {
                Snackbar.make(binding.root, "중요도는 0~4 사이로 입력해주세요.", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            NotificationUtil.createNotification(this, title, message, url, importance)
        }
    }
}