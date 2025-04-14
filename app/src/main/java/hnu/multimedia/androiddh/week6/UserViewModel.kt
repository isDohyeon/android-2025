package hnu.multimedia.androiddh.week6

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hnu.multimedia.androiddh.week6.db.UserEntity
import hnu.multimedia.androiddh.week6.db.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {

    private val userRepository = UserRepository()

    val allUsers = userRepository.getAllUsers()

    fun insertUser(user: UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insert(user)
        }
    }

    fun deleteUser(user: UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.delete(user)
        }
    }
}