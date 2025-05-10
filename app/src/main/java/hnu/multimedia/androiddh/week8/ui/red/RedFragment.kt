package hnu.multimedia.androiddh.week8.ui.red

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hnu.multimedia.androiddh.databinding.FragmentRedBinding
import hnu.multimedia.androiddh.week8.ui.util.ColorUtil

class RedFragment : Fragment() {

    private val binding by lazy { FragmentRedBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.buttonChange.setOnClickListener {
            val randomColor = ColorUtil.getRandomColor()
            binding.textViewRed.text = ColorUtil.getColorName(randomColor)
            binding.layoutRed.setBackgroundColor(Color.parseColor(randomColor))
        }
        return binding.root
    }
}