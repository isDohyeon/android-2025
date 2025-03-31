package hnu.multimedia.androiddh.week4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hnu.multimedia.androiddh.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {

    private val binding by lazy { ActivityMain4Binding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}