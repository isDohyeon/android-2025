package hnu.multimedia.androiddh.week5

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hnu.multimedia.androiddh.databinding.ActivityMain5Binding

class Main5Activity : AppCompatActivity() {

    private val binding by lazy { ActivityMain5Binding.inflate(layoutInflater) }
    private val examViewModel: ExamViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = RVAdapter(mutableListOf())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        examViewModel.examModelList.observe(this) {
            adapter.updateList(it)
        }

        binding.buttonAdd.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("mode", "add")
            addExamLauncher.launch(intent)
        }
    }

    private val addExamLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val newExam = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getSerializableExtra("newExam", ExamModel::class.java)
            } else {
                @Suppress("DEPRECATION")
                result.data?.getSerializableExtra("newExam") as? ExamModel
            }
            newExam?.let {
                examViewModel.addExam(it)
            }
        }
    }
}
