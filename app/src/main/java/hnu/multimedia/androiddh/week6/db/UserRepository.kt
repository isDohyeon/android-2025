package hnu.multimedia.androiddh.week6.db

import androidx.lifecycle.LiveData

class UserRepository {

    private val database = AppDatabase.getDatabase()

    fun getAllUsers(): LiveData<MutableList<UserEntity>> {
        return database.userDao().getAllUsers()
    }

    suspend fun update(user: UserEntity) {
        database.userDao().update(user)
    }

    suspend fun insert(user: UserEntity) {
        database.userDao().insert(user)
    }

    suspend fun delete(user: UserEntity) {
        database.userDao().delete(user)
    }
}
