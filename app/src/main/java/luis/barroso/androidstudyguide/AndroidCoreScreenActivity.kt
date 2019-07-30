package luis.barroso.androidstudyguide

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_android_core_screen.*

class AndroidCoreScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_core_screen)

        button_toast.setOnClickListener { goToToastScreen() }
        button_snackbar.setOnClickListener { goToSnackbarScreen() }
        button_localization.setOnClickListener { goToLocalizationScreen() }
        button_notifications.setOnClickListener { goToNotificationsScreen() }
    }

    private fun goToToastScreen() = startActivity(Intent(this, AndroidCoreToastsScreenActivity::class.java))

    private fun goToSnackbarScreen() = startActivity(Intent(this, AndroidCoreSnackbarScreenActivity::class.java))

    private fun goToLocalizationScreen() = startActivity(Intent(this, AndroidCoreLozalizationScreenActivity::class.java))

    private fun goToNotificationsScreen() = startActivity(Intent(this, AndroidCoreNotificationsScreenActivity::class.java))
}
