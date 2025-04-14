package hnu.multimedia.androiddh.week6.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT * FROM userentity")
    fun getAllUsers(): LiveData<MutableList<UserEntity>>

    @Update
    suspend fun update(user: UserEntity)

    @Insert
    suspend fun insert(user: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)
}
