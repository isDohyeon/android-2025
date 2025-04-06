package hnu.multimedia.androiddh.week5

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hnu.multimedia.androiddh.databinding.ItemBinding

class RVAdapter(private val list: List<Item>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemBinding.inflate(inflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textViewItemName.text = list[position].name
        holder.binding.textViewItemMood.text = list[position].mood
        holder.binding.imageViewItemPhoto.setImageResource(list[position].photo)

        holder.binding.layout.setOnClickListener {
            // Intent : 명령 객체 -> 어떤 액티비티로 어떤 데이터를 전달하여 넘길 것인가?
            // Intent(지금 아이템이 붙어있는 화면의 context(어디서 출발?), 어느 액티비티로 이동할지(어디로 도착?)
            val intent = Intent(holder.binding.root.context, SubActivity::class.java)
            // 명령에 넘길 데이터를 put
            intent.putExtra("photo", list[position].photo)
            intent.putExtra("name", list[position].name)
            intent.putExtra("mood", list[position].mood)
            // Context : 현재 화면에 대한 다양한 정보
            val context = holder.binding.root.context
            // 이 context를 바탕으로 Intent를 실행
            context.startActivity(intent)
        }
    }

}