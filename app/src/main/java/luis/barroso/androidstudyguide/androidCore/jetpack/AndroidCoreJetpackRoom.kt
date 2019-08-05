package luis.barroso.androidstudyguide.androidCore.jetpack

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_android_core_jetpack_room.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import luis.barroso.androidstudyguide.R
import luis.barroso.androidstudyguide.androidCore.jetpack.room.RoomDatabaseManager
import luis.barroso.androidstudyguide.androidCore.jetpack.room.UserAdapter
import luis.barroso.androidstudyguide.androidCore.jetpack.room.UserEntity

class AndroidCoreJetpackRoom : AppCompatActivity() {

    private val adapter = UserAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_core_jetpack_room)

        button_add_user.setOnClickListener { addUser() }

        recycler_view_users.layoutManager = LinearLayoutManager(this)
        recycler_view_users.adapter = adapter

        getAllUsersFromDataBase()
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

        RoomDatabaseManager.insertUser(this, UserEntity(name = name, lastName = lastName, age = age)){
            getAllUsersFromDataBase()
            cleanFields()
            hideKeyboard()
        }

    }

    private fun getAllUsersFromDataBase(){
        RoomDatabaseManager.getUsers(this){listUsers ->
            adapter.items = listUsers
            adapter.notifyDataSetChanged()
        }
    }

    private fun cleanFields(){
        text_input_edit_text_name.text?.clear()
        text_input_edit_text_last_name.text?.clear()
        text_input_edit_text_age.text?.clear()
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
    }

    fun Context.hideKeyboard(view: View) {

        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

    }
}
