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
            if (id.isEmpty() && password.isEmpty()) {
                Snackbar.make(binding.root, "ID와 비밀번호를 입력해주세요", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (id.isEmpty()) {
                Snackbar.make(binding.root, "ID를 입력해주세요", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                Snackbar.make(binding.root, "암호를 입력해주세요", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            Snackbar.make(binding.root, "ID: $id\n암호: $password", Snackbar.LENGTH_LONG).show()
        }
    }
}