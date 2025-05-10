package hnu.multimedia.androiddh.week9

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import hnu.multimedia.androiddh.databinding.ItemColorBinding

class ColorAdapter(
    private val list: List<ColorModel>
) : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemColorBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemColorBinding = ItemColorBinding.inflate(inflater, parent, false)
        return ViewHolder(itemColorBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val color = list[position].color
        val hexCode = list[position].hexCode

        holder.binding.layoutBackground.setBackgroundColor(color)
        holder.binding.textColorCode.text = hexCode

        holder.binding.root.setOnClickListener {
            FirebaseRef.favoriteColors.push().setValue(ColorModel(color, hexCode))
            Snackbar.make(
                holder.itemView,
                "색상 $hexCode 가 추가되었습니다",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}