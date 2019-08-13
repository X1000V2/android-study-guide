package luis.barroso.androidstudyguide.androidCore

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_android_core_jetpack_screen.*
import luis.barroso.androidstudyguide.R
import luis.barroso.androidstudyguide.androidCore.jetpack.navigation.AndroidCoreJetpackNavigationActivity
import luis.barroso.androidstudyguide.androidCore.jetpack.room.AndroidCoreJetpackRoom
import luis.barroso.androidstudyguide.androidCore.jetpack.workmanager.AndroidCoreJetpackWorkManagerActivity
import luis.barroso.androidstudyguide.androidCore.jetpack.viewmodel.AndroidCoreJetpackViewmodelActivity

class AndroidCoreJetpackScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_core_jetpack_screen)

        button_work_manager.setOnClickListener { goToWorkManagerScreen() }
        button_room.setOnClickListener { goToRoomScreen() }
        button_navigation.setOnClickListener { goToNavigationScreen() }
        button_viewmodel.setOnClickListener { goToViewmodeScreen() }
    }

    private fun goToWorkManagerScreen() = startActivity(Intent(this, AndroidCoreJetpackWorkManagerActivity::class.java))

    private fun goToRoomScreen() = startActivity(Intent(this, AndroidCoreJetpackRoom::class.java))

    private fun goToNavigationScreen() = startActivity(Intent(this, AndroidCoreJetpackNavigationActivity::class.java))

    private fun goToViewmodeScreen() = startActivity(Intent(this, AndroidCoreJetpackViewmodelActivity::class.java))
}
