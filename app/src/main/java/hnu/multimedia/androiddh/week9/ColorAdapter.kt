package hnu.multimedia.androiddh.week9

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import hnu.multimedia.androiddh.databinding.ItemColorBinding

class ColorAdapter(
    private val list: List<ColorModel>,
    private val isFavoriteList: Boolean = false
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
        val colorModel = list[position]
        val color = colorModel.color
        val hexCode = colorModel.hexCode

        holder.binding.layoutBackground.setBackgroundColor(color)
        holder.binding.textColorCode.text = hexCode

        holder.binding.root.setOnClickListener {
            if (!isFavoriteList) {
                val newRef = FirebaseRef.favoriteColors.push()
                newRef.setValue(ColorModel(color, hexCode, newRef.key.toString()))
                Snackbar.make(
                    holder.itemView,
                    "색상 $hexCode 가 추가되었습니다",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        holder.binding.root.setOnLongClickListener {
            if (isFavoriteList && colorModel.uid.isNotEmpty()) {
                FirebaseRef.favoriteColors.child(colorModel.uid).removeValue()
                Snackbar.make(
                    holder.itemView,
                    "색상 $hexCode 가 삭제되었습니다",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            true
        }
    }
}