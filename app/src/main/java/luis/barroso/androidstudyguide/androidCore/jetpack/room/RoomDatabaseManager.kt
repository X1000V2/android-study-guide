package luis.barroso.androidstudyguide.androidCore.jetpack.room

import android.app.Application
import android.content.Context
import androidx.annotation.WorkerThread
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomDatabaseManager {

    companion object{

        private val DATA_BASE_NAME = "databasename"
        private var database : AppDatabase? = null

        private fun getInstance(context: Context):AppDatabase?{

            if(database == null) {
                database = Room.databaseBuilder(context, AppDatabase::class.java, DATA_BASE_NAME).build()
            }
            return database
        }


        fun insertUser(context: Context, user: UserEntity){
            GlobalScope.launch {
                getInstance(context)?.userDao()?.insertAll(user)

                val users = RoomDatabaseManager.getInstance(context)?.userDao()?.getAll()?.toList()
                users?.forEach{user ->
                    println("User[${user.id}]: ${user.name} - ${user.lastName} - ${user.age}")
                }
            }
        }
    }
}