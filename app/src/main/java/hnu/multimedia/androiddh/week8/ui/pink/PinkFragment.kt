package hnu.multimedia.androiddh.week8.ui.pink

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hnu.multimedia.androiddh.databinding.FragmentPinkBinding
import hnu.multimedia.androiddh.week8.ui.util.ColorUtil

class PinkFragment : Fragment() {

    private val binding by lazy { FragmentPinkBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.buttonChange.setOnClickListener {
            val randomColor = ColorUtil.getRandomColor()
            binding.textViewPink.text = ColorUtil.getColorName(randomColor)
            binding.layoutPink.setBackgroundColor(Color.parseColor(randomColor))
        }
        return binding.root
    }
}