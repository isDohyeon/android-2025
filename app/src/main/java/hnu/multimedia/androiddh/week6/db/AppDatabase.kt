package hnu.multimedia.androiddh.week6.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hnu.multimedia.androiddh.week6.AppContext

@Database(entities = [UserEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    AppContext.get(),
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
