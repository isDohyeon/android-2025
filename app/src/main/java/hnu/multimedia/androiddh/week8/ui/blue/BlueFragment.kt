package hnu.multimedia.androiddh.week8.ui.blue

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hnu.multimedia.androiddh.databinding.FragmentBlueBinding
import hnu.multimedia.androiddh.week8.ui.util.ColorUtil


class BlueFragment : Fragment() {
    private val binding by lazy { FragmentBlueBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.buttonChange.setOnClickListener {
            val randomColor = ColorUtil.getRandomColor()
            binding.textViewBlue.text = ColorUtil.getColorName(randomColor)
            binding.layoutBlue.setBackgroundColor(Color.parseColor(randomColor))
        }
        return binding.root
    }
}