package hnu.multimedia.androiddh.week4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import hnu.multimedia.androiddh.R
import hnu.multimedia.androiddh.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    private val binding by lazy { FragmentInputBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.button.setOnClickListener {
            val height = binding.editTextID.text.toString().toDoubleOrNull()
            val weight = binding.editTextName.text.toString().toDoubleOrNull()
            if (validateInput(height, weight)) return@setOnClickListener
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, ResultFragment.newInstance(height!!, weight!!))
            transaction.commit()
        }
        return binding.root
    }

    private fun validateInput(height: Double?, weight: Double?): Boolean {
        if (height == null && weight == null) {
            Snackbar.make(binding.root, "올바른 키와 몸무게를 입력해주세요", Snackbar.LENGTH_LONG).show()
            return true
        }
        if (height == null) {
            Snackbar.make(binding.root, "올바른 키를 입력해주세요", Snackbar.LENGTH_LONG).show()
            return true
        }
        if (weight == null) {
            Snackbar.make(binding.root, "올바른 몸무게를 입력해주세요", Snackbar.LENGTH_LONG).show()
            return true
        }
        return false
    }
}