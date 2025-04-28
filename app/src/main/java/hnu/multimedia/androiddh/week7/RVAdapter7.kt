package hnu.multimedia.androiddh.week7

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.database.database
import hnu.multimedia.androiddh.databinding.Item7Binding

class RVAdapter7(val list: List<DockModel>): RecyclerView.Adapter<RVAdapter7.ViewHolder>() {

    class ViewHolder(val binding: Item7Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item7Binding = Item7Binding.inflate(inflater, parent, false)
        return ViewHolder(item7Binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.textViewItemTitle7.text = list[position].title
        holder.binding.textViewItemContent7.text = list[position].content

        holder.itemView.setOnLongClickListener {
            val database = Firebase.database
            val ref = database.getReference("board")
            ref.child(item.key).removeValue()
            true
        }
    }
}