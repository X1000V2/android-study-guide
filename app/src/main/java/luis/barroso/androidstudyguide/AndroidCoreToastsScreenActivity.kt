package luis.barroso.androidstudyguide

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_android_core_toasts_screen.*
import android.widget.TextView



class AndroidCoreToastsScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_core_toasts_screen)

        button_toast_simple.setOnClickListener { showToast(TOAST_TYPE.SIMPLE) }
        button_toast_positioned.setOnClickListener { showToast(TOAST_TYPE.POSITIONED) }
        button_toast_custom.setOnClickListener { showToast(TOAST_TYPE.CUSTOM) }
    }

    private fun showToast(type: TOAST_TYPE) {
        when(type){
            TOAST_TYPE.SIMPLE -> Toast.makeText(this, getText(R.string.android_core_screen_toast_simple), Toast.LENGTH_SHORT).show()
            TOAST_TYPE.POSITIONED -> {
                val toast = Toast.makeText(this, getText(R.string.android_core_screen_toast_positioned), Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP,0,0)
                toast.show()
            }
            TOAST_TYPE.CUSTOM -> {

                val inflater = layoutInflater
                val layout = inflater.inflate(R.layout.custom_toast_layout, findViewById<ViewGroup>(R.id.custom_toast_container))

                with(Toast(applicationContext)){
                    setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                    duration = Toast.LENGTH_LONG
                    view = layout
                    show()
                }
            }
            else -> {}
        }
    }

    enum class TOAST_TYPE{
        SIMPLE, POSITIONED, CUSTOM
    }
}
