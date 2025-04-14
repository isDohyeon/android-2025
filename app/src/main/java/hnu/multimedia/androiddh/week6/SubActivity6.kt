package hnu.multimedia.androiddh.week6

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import hnu.multimedia.androiddh.databinding.ActivitySub6Binding
import hnu.multimedia.androiddh.week6.db.UserEntity

class SubActivity6 : AppCompatActivity() {

    private val binding by lazy { ActivitySub6Binding.inflate(layoutInflater) }
    private val userViewModel: UserViewModel by viewModels()
    private var userId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        userId = intent.getLongExtra("user_id", 0)
        val title = intent.getStringExtra("title") ?: ""
        val date = intent.getStringExtra("date") ?: ""
        val memo = intent.getStringExtra("memo") ?: ""

        binding.editTextTitle.setText(title)
        binding.editTextDate.setText(date)
        binding.editTextRange.setText(memo)

        binding.buttonSubBack.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val date = binding.editTextDate.text.toString()
            val memo = binding.editTextRange.text.toString()

            val updatedUser = UserEntity(
                id = userId,
                title = title,
                date = date,
                memo = memo
            )

            userViewModel.updateUser(updatedUser)

            val resultIntent = Intent()
            resultIntent.putExtra("updated", true)
            setResult(RESULT_OK, resultIntent)

            finish()
        }
    }
}