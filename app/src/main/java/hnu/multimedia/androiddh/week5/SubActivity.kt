package hnu.multimedia.androiddh.week5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hnu.multimedia.androiddh.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySubBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // SubActivity가 실행될 때 Intent(명령)에 들어있는 데이터를 가져옴
        binding.imageViewPhoto.setImageResource(intent.getIntExtra("photo", 0))
        binding.textViewSubMood.text = intent.getStringExtra("mood")
        binding.textViewSubName.text = intent.getStringExtra("name")
    }
}