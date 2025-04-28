package hnu.multimedia.androiddh.week6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import hnu.multimedia.androiddh.databinding.ActivityMain6Binding
import hnu.multimedia.androiddh.week6.db.UserEntity

class Main6Activity : AppCompatActivity() {

    private val binding by lazy { ActivityMain6Binding.inflate(layoutInflater) }
    private lateinit var adapter: UserAdapter6
    private val userViewModel: UserViewModel by viewModels()

    private val editLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val updated = result.data?.getBooleanExtra("updated", false) ?: false
            if (updated) {
                Snackbar.make(binding.root, "내용이 업데이트 되었습니다", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRecyclerView()
        setupObservers()
        setupAddButton()
    }

    private fun setupRecyclerView() {
        adapter = UserAdapter6(
            onClick = { user ->
                val intent = Intent(this, SubActivity6::class.java).apply {
                    putExtra("user_id", user.id)
                    putExtra("title", user.title)
                    putExtra("date", user.date)
                    putExtra("memo", user.memo)
                }
                editLauncher.launch(intent)
            },
            onLongClick = { user, position ->
                userViewModel.deleteUser(user)
                Snackbar.make(
                    binding.root,
                    "${position + 1}번째 목록을 삭제했습니다",
                    Snackbar.LENGTH_SHORT
                ).show()
                true
            }
        )

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
                Snackbar.make(
                    binding.root,
                    "추가됨: ${newUser.title}",
                    Snackbar.LENGTH_SHORT
                ).show()

                binding.editTextTitle6.text.clear()
                binding.editTextTitle2.text.clear()
                binding.editTextTitle3.text.clear()
            }
        }
    }
}