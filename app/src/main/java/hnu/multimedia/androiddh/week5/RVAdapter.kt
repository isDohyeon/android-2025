package hnu.multimedia.androiddh.week5

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hnu.multimedia.androiddh.databinding.Item6Binding

class RVAdapter(private val itemList: MutableList<ExamModel>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    class ViewHolder(val binding: Item6Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = Item6Binding.inflate(inflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textViewItemTitle.text = itemList[position].title
        holder.binding.textViewItemTime.text = itemList[position].date
        holder.binding.textViewItemMemo.text = itemList[position].memo

        holder.binding.layout.setOnClickListener {
            val intent = Intent(holder.binding.root.context, SubActivity::class.java)
            intent.putExtra("mode", "read")
            intent.putExtra("position", position)
            intent.putExtra("title", itemList[position].title)
            intent.putExtra("date", itemList[position].date)
            intent.putExtra("range", itemList[position].range)
            intent.putExtra("classroom", itemList[position].classroom)
            intent.putExtra("memo", itemList[position].memo)
            val context = holder.binding.root.context
            context.startActivity(intent)
        }
    }

    fun updateList(newList: List<ExamModel>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }
}