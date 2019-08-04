package luis.barroso.androidstudyguide.androidCore.jetpack.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "last_name") var lastName: String?,
    @ColumnInfo(name = "age") var age: Int?
)
