package hnu.multimedia.androiddh.week9

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import hnu.multimedia.androiddh.R
import hnu.multimedia.androiddh.databinding.ActivityMain9Binding

class MainActivity9 : AppCompatActivity() {

    private val binding by lazy { ActivityMain9Binding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main9)
        navView.setupWithNavController(navController)
    }
}