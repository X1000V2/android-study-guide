package luis.barroso.androidstudyguide.androidCore.jetpack.room

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_android_core_jetpack_room.*
import luis.barroso.androidstudyguide.R

class AndroidCoreJetpackRoom : AppCompatActivity() {

    private val adapter = UserAdapter(emptyList()){user ->
        deleteUserFromDataBase(user)
    }

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

    private fun deleteUserFromDataBase(user: UserEntity){
        RoomDatabaseManager.deleteuser(this,user){
            getAllUsersFromDataBase()
        }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
    }

    fun Context.hideKeyboard(view: View) {

        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

    }
}
