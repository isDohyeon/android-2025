package hnu.multimedia.androiddh.week5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private val _userModel = MutableLiveData<UserModel>()

    val userModel: LiveData<UserModel> get() = _userModel

    fun changeValue(name: String, age: Int) {
        _userModel.postValue(UserModel(name, age))
    }
}