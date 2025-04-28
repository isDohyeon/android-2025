package hnu.multimedia.androiddh.week7

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import hnu.multimedia.androiddh.R
import hnu.multimedia.androiddh.databinding.ActivityMain7Binding

class Main7Activity : AppCompatActivity() {

    private val binding by lazy { ActivityMain7Binding.inflate(layoutInflater) }
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = Firebase.auth
    }

     fun onClickRegister(view: View) {
         val id = binding.editTextID7.text.toString()
         val password = binding.editTextPassword7.text.toString()

         auth.createUserWithEmailAndPassword(id, password)
             .addOnCompleteListener {
                 if (it.isSuccessful) {
                     Snackbar.make(
                         binding.root,
                         "Registrer Success:  ${auth.currentUser?.uid}",
                         Snackbar.LENGTH_LONG
                     ).show()
                 } else {
                     Snackbar.make(binding.root, "Registrer Failed.", Snackbar.LENGTH_LONG)
                         .show()

                 }
             }
         binding.editTextID7.text.clear()
         binding.editTextPassword7.text.clear()
    }

    fun onCLickLogin(view: View) {
        val id = binding.editTextID7.text.toString()
        val password = binding.editTextPassword7.text.toString()

        auth.signInWithEmailAndPassword(id, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Snackbar.make(
                        binding.root,
                        "로그인 성공:  ${auth.currentUser?.uid}",
                        Snackbar.LENGTH_LONG
                    ).show()
                    val intent = Intent(this, Board7Activity::class.java)
                    startActivity(intent)
                } else {
                    Snackbar.make(binding.root, "로그인 실패", Snackbar.LENGTH_LONG)
                        .show()
                }
            }
    }
}