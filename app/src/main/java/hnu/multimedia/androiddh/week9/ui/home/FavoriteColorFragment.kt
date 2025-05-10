package hnu.multimedia.androiddh.week9.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import hnu.multimedia.androiddh.databinding.FragmentFavoriteColorBinding
import hnu.multimedia.androiddh.week9.ColorAdapter
import hnu.multimedia.androiddh.week9.ColorModel
import hnu.multimedia.androiddh.week9.FirebaseRef

class FavoriteColorFragment : Fragment() {

    private val binding by lazy { FragmentFavoriteColorBinding.inflate(layoutInflater) }
    private val favoriteColorList = mutableListOf<ColorModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        getFavoriteColors()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = ColorAdapter(favoriteColorList)

        return binding.root
    }

    private fun getFavoriteColors() {
        val ref = FirebaseRef.favoriteColors
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                favoriteColorList.clear()
                for (dataModel in snapshot.children) {
                    val value = dataModel.getValue(ColorModel::class.java)
                    value?.let { favoriteColorList.add(value) }
                }
                binding.recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("FavoriteColorFragment", "색깔 정보 조회 실패: ${error.message}")
            }
        }
        ref.addValueEventListener(postListener)
    }
}