package hnu.multimedia.androiddh.week7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.database
import hnu.multimedia.androiddh.databinding.ActivityWrite7Binding

class Write7Activity : AppCompatActivity() {

    private val binding by lazy { ActivityWrite7Binding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonUpload7.setOnClickListener {
            val database = Firebase.database
            val ref = database.getReference("board")

            val newPostRef = ref.push()
            val newKey = newPostRef.key ?: ""

            val newPost = DockModel(
                newKey,
                binding.editTextTitle7.text.toString(),
                binding.editTextContent7.text.toString()
            )

            newPostRef.setValue(newPost)

            finish()
        }
    }
}