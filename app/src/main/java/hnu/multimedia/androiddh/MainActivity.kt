package hnu.multimedia.androiddh

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import hnu.multimedia.androiddh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonClick.setOnClickListener {
            val id = binding.editTextID.text.toString()
            val password = binding.editTextPassword.text.toString()
            Snackbar.make(binding.root, "ID: $id\n암호: $password", Snackbar.LENGTH_LONG).show()
        }
    }
}