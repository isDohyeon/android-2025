package hnu.multimedia.androiddh.week6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hnu.multimedia.androiddh.databinding.ItemBinding
import hnu.multimedia.androiddh.week6.db.UserEntity

class UserAdapter(private val onLongClick: (UserEntity, Int) -> Boolean) :
    ListAdapter<UserEntity, UserAdapter.ViewHolder>(UserDiffCallBack()) {

    class ViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    class UserDiffCallBack: DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemBinding.inflate(inflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.textViewItemTitle.text = item.title
        holder.binding.textViewItemTime.text = item.date
        holder.binding.textViewItemMemo.text = item.memo

        holder.binding.root.setOnLongClickListener {
            onLongClick(item, position)
        }
    }
}