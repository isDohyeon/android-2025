package hnu.multimedia.androiddh.week9.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hnu.multimedia.androiddh.databinding.FragmentRandomColorBinding
import hnu.multimedia.androiddh.week9.ColorAdapter
import hnu.multimedia.androiddh.week9.ColorModel
import kotlin.random.Random

class RandomColorFragment : Fragment() {

    private val binding by lazy { FragmentRandomColorBinding.inflate(layoutInflater) }
    private val randomColorList = mutableListOf<ColorModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        generateColors()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = ColorAdapter(randomColorList)

        return binding.root
    }

    private fun generateColors() {
        randomColorList.clear()
        for (i in 0..100) {
            val color = Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
            val hexCode = String.format("#%06X", (0xFFFFFF and color))
            randomColorList.add(ColorModel(color, hexCode))
        }
    }
}