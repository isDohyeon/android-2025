package hnu.multimedia.androiddh.week4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hnu.multimedia.androiddh.R
import hnu.multimedia.androiddh.databinding.FragmentResultBinding
import kotlin.math.round

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ResultFragment : Fragment() {

    private val binding by lazy { FragmentResultBinding.inflate(layoutInflater) }
    private var height: Double = 0.0
    private var weight: Double = 0.0

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
    ): View {
        // TODO
        val bmi = weight / (height * height)
        val (result, emojiResId) = getBmiResult(bmi)
        val heightCm = height * 100.0
        val (min, max) = getNormalWeightRange(heightCm)
        val normalRangeText = "키 ${heightCm}cm의 정상 체중은\n${min}kg ~ ${max}kg 입니다."
        val adjustmentText = getWeightAdjustment(heightCm, weight)
        setViews(result, normalRangeText, adjustmentText, emojiResId)

        return binding.root
    }

    private fun setViews(
        result: String,
        normalRangeText: String,
        adjustmentText: String,
        emojiResId: Int
    ) {
        binding.textResult.text = result
        binding.textContent.text = buildString {
            append(normalRangeText)
            append("\n")
            append(adjustmentText)
        }
        binding.emojiView.setImageResource(emojiResId)
        binding.buttonBack.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, InputFragment())
            transaction.commit()
        }
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

    private fun getNormalWeightRange(heightCm: Double): Pair<Double, Double> {
        val heightM = heightCm / 100
        val minWeight = round(18.5 * heightM * heightM * 10) / 10
        val maxWeight = round(22.9 * heightM * heightM * 10) / 10
        return Pair(minWeight, maxWeight)
    }

    private fun getWeightAdjustment(heightCm: Double, currentWeight: Double): String {
        val (minWeight, maxWeight) = getNormalWeightRange(heightCm)
        return when {
            currentWeight < minWeight -> {
                val gain = minWeight - currentWeight
                "정상 체중까지\n 약 %.1fkg 증량이 필요합니다.".format(gain)
            }
            currentWeight > maxWeight -> {
                val loss = currentWeight - maxWeight
                "정상 체중까지\n 약 %.1fkg 감량이 필요합니다.".format(loss)
            }
            else -> "정상 체중입니다! 유지하세요!"
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