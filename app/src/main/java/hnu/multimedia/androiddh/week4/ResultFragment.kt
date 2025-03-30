package hnu.multimedia.androiddh.week4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hnu.multimedia.androiddh.R
import hnu.multimedia.androiddh.databinding.FragmentResultBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ResultFragment : Fragment() {

    private val binding by lazy { FragmentResultBinding.inflate(layoutInflater) }
    private var height: Double? = null
    private var weight: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            height = it.getDouble(ARG_PARAM1)
            weight = it.getDouble(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.buttonBack.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, InputFragment())
            transaction.commit()
        }
        val bmi = weight!! / (height!! * height!!)
        val (result, emojiResId) = getBmiResult(bmi)

        binding.textResult.text = result
        binding.textContent.text = ""
        binding.emojiView.setImageResource(emojiResId)

        return binding.root
    }

    private fun getBmiResult(bmi: Double): Pair<String, Int> {
        return when {
            bmi < 18.5 -> "저체중" to R.drawable.baseline_fastfood_24
            bmi < 23 -> "정상" to R.drawable.baseline_emoji_emotions_24
            bmi < 25 -> "과체중" to R.drawable.baseline_fmd_bad_24
            bmi < 30 -> "1단계 비만" to R.drawable.baseline_directions_run_24
            bmi < 35 -> "2단계 비만" to R.drawable.baseline_directions_run_24
            else -> "3단계 비만" to R.drawable.baseline_directions_run_24
        }
    }

    companion object {
        @JvmStatic fun newInstance(param1: Double, param2: Double) =
                ResultFragment().apply {
                    arguments = Bundle().apply {
                        putDouble(ARG_PARAM1, param1 / 100)
                        putDouble(ARG_PARAM2, param2)
                    }
                }
    }
}