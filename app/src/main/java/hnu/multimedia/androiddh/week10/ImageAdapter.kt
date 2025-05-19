package hnu.multimedia.androiddh.week10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import hnu.multimedia.androiddh.databinding.Item10Binding

class ImageAdapter(private val list: MutableList<ImageModel>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    class ViewHolder(val binding: Item10Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item10Binding = Item10Binding.inflate(inflater, parent, false)
        return ViewHolder(item10Binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = list[position]

        Glide.with(holder.binding.imageViewPhoto.context)
            .load(image.downloadUri)
            .into(holder.binding.imageViewPhoto)

        holder.binding.root.setOnLongClickListener {
            Firebase.storage.reference.child(image.name)
                .delete()
                .addOnSuccessListener {
                    list.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, list.size)
                    Snackbar.make(holder.binding.root, "삭제 완료", Snackbar.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Snackbar.make(holder.binding.root, "삭제 실패: ${e.message}", Snackbar.LENGTH_SHORT).show()
                }
            true
        }
    }

    override fun getItemCount(): Int = list.size
}