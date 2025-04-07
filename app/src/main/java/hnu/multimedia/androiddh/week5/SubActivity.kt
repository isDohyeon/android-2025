package hnu.multimedia.androiddh.week5

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import hnu.multimedia.androiddh.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySubBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val mode = intent.getStringExtra("mode")
        if (mode.equals("read")) {
            binding.buttonSubBack.visibility = View.VISIBLE
            binding.buttonSubAdd.visibility = View.GONE
            setTexts()
            binding.buttonSubBack.setOnClickListener {
                finish()
            }
        } else if (mode.equals("add")) {
            binding.buttonSubBack.visibility = View.GONE
            binding.buttonSubAdd.visibility = View.VISIBLE
            binding.buttonSubAdd.setOnClickListener {
                val exam = readExamInfo()
                val resultIntent = Intent()
                resultIntent.putExtra("newExam", exam)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    private fun setTexts() {
        binding.editTextTitle.setText(intent.getStringExtra("title"))
        binding.editTextDate.setText(intent.getStringExtra("date"))
        binding.editTextClassroom.setText(intent.getStringExtra("classroom"))
        binding.editTextRange.setText(intent.getStringExtra("range"))
        binding.editTextMemo.setText(intent.getStringExtra("memo"))
    }

    private fun readExamInfo(): ExamModel {
        val title = binding.editTextTitle.text.toString()
        val date = binding.editTextDate.text.toString()
        val classroom = binding.editTextClassroom.text.toString()
        val range = binding.editTextRange.text.toString()
        val memo = binding.editTextMemo.text.toString()
        return ExamModel(title, date, memo, classroom, range)
    }
}