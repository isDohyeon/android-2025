package hnu.multimedia.androiddh.week8.ui.yellow

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hnu.multimedia.androiddh.databinding.FragmentYellowBinding
import hnu.multimedia.androiddh.week8.ui.util.ColorUtil

class YellowFragment : Fragment() {

    private val binding by lazy { FragmentYellowBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.buttonChange.setOnClickListener {
            val randomColor = ColorUtil.getRandomColor()
            binding.textViewYellow.text = ColorUtil.getColorName(randomColor)
            binding.layoutYellow.setBackgroundColor(Color.parseColor(randomColor))
        }
        return binding.root
    }
}