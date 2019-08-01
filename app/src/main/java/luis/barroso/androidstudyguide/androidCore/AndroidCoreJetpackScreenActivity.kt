package luis.barroso.androidstudyguide.androidCore

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_android_core_jetpack_screen.*
import luis.barroso.androidstudyguide.R
import luis.barroso.androidstudyguide.androidCore.jetpack.AndroidCoreJetpackWorkManagerActivity

class AndroidCoreJetpackScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_core_jetpack_screen)

        button_work_manager.setOnClickListener { goToWorkManagerScreen() }
    }

    private fun goToWorkManagerScreen() = startActivity(Intent(this, AndroidCoreJetpackWorkManagerActivity::class.java))
}
