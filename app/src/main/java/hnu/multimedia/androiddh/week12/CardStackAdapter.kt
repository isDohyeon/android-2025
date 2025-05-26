package hnu.multimedia.androiddh.week12

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hnu.multimedia.androiddh.databinding.ItemCard12Binding

class CardStackAdapter(private val list: MutableList<SpotModel>) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCard12Binding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemCard12Binding.inflate(inflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = list[position]
        holder.binding.textViewCity.text = card.city
        holder.binding.textViewContent.text = card.content
        Glide.with(holder.binding.imageViewPhoto.context)
            .load(card.url)
            .into(holder.binding.imageViewPhoto)
    }

    fun addCard(card: SpotModel) {
        list.add(card)
        notifyItemInserted(list.size - 1)
    }
}