package luis.barroso.androidstudyguide.androidCore.jetpack

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_android_core_jetpack_room.*
import luis.barroso.androidstudyguide.R
import luis.barroso.androidstudyguide.androidCore.jetpack.room.RoomDatabaseManager
import luis.barroso.androidstudyguide.androidCore.jetpack.room.UserEntity

class AndroidCoreJetpackRoom : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_core_jetpack_room)

        button_add_user.setOnClickListener { addUser() }
    }

    private fun addUser() {

        if (!text_input_edit_text_name.text!!.isEmpty() && !text_input_edit_text_last_name.text!!.isEmpty()
            && !text_input_edit_text_age.text!!.isEmpty()) {
            insertUserInDataBase(
                text_input_edit_text_name.text.toString(),
                text_input_edit_text_last_name.text.toString(),
                text_input_edit_text_age.text.toString().toInt()
            )
        } else {
            Toast.makeText(
                this,
                getString(R.string.android_core_screen_jetpack_room_error_empty_fields),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun insertUserInDataBase(name: String, lastName: String, age: Int) {

        RoomDatabaseManager.insertUser(this, UserEntity(name = name, lastName = lastName, age = age))

    }
}
