package hnu.multimedia.androiddh.week10

import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import hnu.multimedia.androiddh.databinding.ActivityMain10Binding
import java.util.*

class MainActivity10 : AppCompatActivity() {

    private val binding by lazy { ActivityMain10Binding.inflate(layoutInflater) }

    private val list = mutableListOf<ImageModel>()
    private lateinit var adapter: ImageAdapter
    private val storageRef = Firebase.storage.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadImagesUri()
        adapter = ImageAdapter(list)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = adapter

        val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            uri ->
            uri?.let {
                uploadImage(uri)
            }
        }

        binding.imageViewGallery.setOnClickListener {
            launcher.launch("image/*")
        }
    }

    private fun loadImagesUri() {
        storageRef.listAll()
            .addOnSuccessListener { listResult ->
                list.clear()
                for (item in listResult.items) {
                    val imageName = item.name
                    item.downloadUrl.addOnSuccessListener { downloadUrl ->
                        list.add(ImageModel(imageName, downloadUrl.toString()))
                        adapter.notifyDataSetChanged()
                    }
                }
            }
    }

    private fun uploadImage(fileUri: Uri) {
        val fileName = "${UUID.randomUUID()}.jpg"
        val imageRef = storageRef.child(fileName)

        imageRef.putFile(fileUri)
            .addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                    list.add(ImageModel(fileName, downloadUri.toString()))
                    adapter.notifyDataSetChanged()
                    Snackbar.make(binding.root, "업로드 성공!", Snackbar.LENGTH_SHORT).show()
                }
            }
    }
}