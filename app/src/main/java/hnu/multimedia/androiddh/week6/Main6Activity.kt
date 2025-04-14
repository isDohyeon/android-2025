package hnu.multimedia.androiddh.week6

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import hnu.multimedia.androiddh.databinding.ActivityMain6Binding
import hnu.multimedia.androiddh.week6.db.UserEntity

class Main6Activity : AppCompatActivity() {
    private val binding by lazy { ActivityMain6Binding.inflate(layoutInflater) }
    private lateinit var adapter: UserAdapter
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRecyclerView()
        setupObservers()
        setupAddButton()
    }

    private fun setupRecyclerView() {
        adapter = UserAdapter { user, position ->
            userViewModel.deleteUser(user)
            showDeleteSnackbar(position)
            true
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupObservers() {
        userViewModel.allUsers.observe(this) { users ->
            adapter.submitList(users)
        }
    }

    private fun setupAddButton() {
        binding.buttonAddUser.setOnClickListener {
            val title = binding.editTextTitle6.text.toString()
            val date = binding.editTextTitle2.text.toString()
            val memo = binding.editTextTitle3.text.toString()

            if(title.isNotEmpty() && date.isNotEmpty()) {
                val newUser = UserEntity(
                    title = title,
                    date = date,
                    memo = memo
                )
                userViewModel.insertUser(newUser)
                binding.editTextTitle6.text.clear()
                binding.editTextTitle2.text.clear()
                binding.editTextTitle3.text.clear()
            }
        }
    }

    private fun showDeleteSnackbar(position: Int) {
        Snackbar.make(
            binding.root,
            "${position + 1} 번째 메모가 지워졌습니다.",
            Snackbar.LENGTH_LONG
        ).show()
    }
}