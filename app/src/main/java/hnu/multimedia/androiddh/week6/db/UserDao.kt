package hnu.multimedia.androiddh.week6.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: UserEntity)

    @Query("SELECT * FROM userentity")
    fun getAllUsers(): LiveData<MutableList<UserEntity>>

    @Delete
    suspend fun delete(user: UserEntity)
}
