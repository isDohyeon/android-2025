package hnu.multimedia.androiddh.week5

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import hnu.multimedia.androiddh.R
import hnu.multimedia.androiddh.databinding.ActivityMain5Binding

class Main5Activity : AppCompatActivity() {

    private val binding by lazy { ActivityMain5Binding.inflate(layoutInflater) }

    private val userModel = UserModel("hyeondo", 100)
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // viewModel을 사용한 방법.
        // 바뀐 데이터가 UI에 정상적으로 적용됨
        binding.buttonViewModel.setOnClickListener {
            viewModel.changeValue("도현", 25)
        }
        viewModel.userModel.observe(this) {
            binding.textViewName.text = it.name
            binding.textViewAge.text = it.age.toString()
        }

        val list = mutableListOf<Item>()
        repeat(30){
            list.add(Item(R.drawable.baseline_directions_run_24, "Lee", "Good"))
            list.add(Item(R.drawable.baseline_emoji_emotions_24, "Choi", "Happy"))
            list.add(Item(R.drawable.baseline_fastfood_24, "Park", "Yummy"))
            list.add(Item(R.drawable.baseline_fmd_bad_24, "Kim", "Bad"))
        }
        binding.recyclerView.adapter = RVAdapter(list)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
