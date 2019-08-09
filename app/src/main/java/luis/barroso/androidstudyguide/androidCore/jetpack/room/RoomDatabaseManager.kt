package luis.barroso.androidstudyguide.androidCore.jetpack.room

import android.app.Application
import android.content.Context
import androidx.annotation.WorkerThread
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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


        fun insertUser(context: Context, user: UserEntity, success:() -> Unit){
            GlobalScope.launch {
                getInstance(context)?.userDao()?.insertAll(user)

                val users = RoomDatabaseManager.getInstance(context)?.userDao()?.getAll()?.toList()
                users?.forEach{user ->
                    println("User[${user.id}]: ${user.name} - ${user.lastName} - ${user.age}")
                }
                withContext(Dispatchers.Main) {
                    success()
                }
            }
        }

        fun getUsers(context: Context, success:(result: List<UserEntity>) -> Unit){
            GlobalScope.launch {
                val result = getInstance(context)?.userDao()?.getAll()

                withContext(Dispatchers.Main) {
                    // update UI here
                    success(result?.toMutableList()?: emptyList())
                }
            }
        }

        fun deleteuser(context: Context, user: UserEntity, success: () -> Unit){

            GlobalScope.launch {
                getInstance(context)?.userDao()?.delete(user)
                withContext(Dispatchers.Main) {
                    success()
                }
            }
        }
    }
}