package luis.barroso.androidstudyguide.androidCore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_android_core_snackbar_screen.*
import luis.barroso.androidstudyguide.R

class AndroidCoreSnackbarScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_core_snackbar_screen)

        button_snackbar_simple.setOnClickListener { showSnackbar(SNACKBAR_TYPE.SIMPLE) }
        button_snackbar_simple_with_action.setOnClickListener { showSnackbar(SNACKBAR_TYPE.SIMPLE_WITH_ACTION) }
    }

    private fun showSnackbar(type: SNACKBAR_TYPE) {
        when(type){
            SNACKBAR_TYPE.SIMPLE -> Snackbar.make(container_root, getString(
                R.string.android_core_screen_snackbar_simple
            ), Snackbar.LENGTH_LONG).show()
            SNACKBAR_TYPE.SIMPLE_WITH_ACTION -> {
                val snackbarWithAction = Snackbar.make(container_root, getString(R.string.android_core_screen_snackbar_simple), Snackbar.LENGTH_LONG)
                snackbarWithAction.setAction(getString(R.string.android_core_screen_snackbar_simple_with_action_done)){
                    snackbarWithAction.dismiss()
                }
                snackbarWithAction.show()
            }
        }
    }

    enum class SNACKBAR_TYPE{
        SIMPLE, SIMPLE_WITH_ACTION
    }
}
