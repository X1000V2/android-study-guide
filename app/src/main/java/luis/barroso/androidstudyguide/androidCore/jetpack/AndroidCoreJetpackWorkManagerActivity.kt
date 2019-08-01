package luis.barroso.androidstudyguide.androidCore.jetpack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_android_core_jetpack_work_manager.*
import luis.barroso.androidstudyguide.R
import luis.barroso.androidstudyguide.androidCore.jetpack.workmanager.DataWorkManager
import java.util.concurrent.TimeUnit

class AndroidCoreJetpackWorkManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_core_jetpack_work_manager)



        button_task_one.setOnClickListener { doTaskOne() }

    }

    private fun doTaskOne(){

        val dataWorkRequest = OneTimeWorkRequestBuilder<DataWorkManager>().build()
        text_view_task_1.text = getText(R.string.android_core_screen_jetpack_work_manager_task_doing)

        //livedate to update text tast 1
        WorkManager.getInstance()
            .getWorkInfoByIdLiveData(dataWorkRequest.id)
            .observe(this, Observer { status ->
                if (status?.state?.isFinished == true) {
                    // job finished
                    text_view_task_1.text = getText(R.string.android_core_screen_jetpack_work_manager_task_done)
                }
            })

        WorkManager.getInstance().enqueue(dataWorkRequest)

    }
}
