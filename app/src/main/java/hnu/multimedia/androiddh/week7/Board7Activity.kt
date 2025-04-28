package hnu.multimedia.androiddh.week7

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.ktx.database
import hnu.multimedia.androiddh.databinding.ActivityBoard7Binding
class Board7Activity : AppCompatActivity() {

    private val binding by lazy { ActivityBoard7Binding.inflate(layoutInflater) }

    private val list = mutableListOf<DockModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = RVAdapter7(list)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.buttonWrite7.setOnClickListener {
            val intent = Intent(this, Write7Activity::class.java)
            startActivity(intent)
        }
        getData()
    }

    private fun getData() {
        val database = Firebase.database
        val ref = database.getReference("board")

        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (dataModel in snapshot.children) {
                    val value = dataModel.getValue(DockModel::class.java)
                    val key = dataModel.key
                    value?.let {
                        val newItem = DockModel(key ?: "", it.title, it.content)
                        list.add(newItem)
                    }
                }
                binding.recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("BoardActivity", "onCancelled: ${error.toException()}")
            }
        }
        ref.addValueEventListener(postListener)
    }
}