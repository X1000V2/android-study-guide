package luis.barroso.androidstudyguide.androidCore.jobScheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_android_core_job_scheduler.*
import luis.barroso.androidstudyguide.R


class AndroidCoreJobSchedulerActivity : AppCompatActivity() {

    var mScheduler: JobScheduler? = null
    val JOB_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_core_job_scheduler)

        button_schedule_job.setOnClickListener { scheduleJob() }
        button_cancel_job.setOnClickListener { canceljob() }
    }

    private fun scheduleJob() {
        val selectedNetwork = radio_group_network.checkedRadioButtonId
        val selectedNetworkOption: Int

        when (selectedNetwork) {
            R.id.radio_button_netwotk_none -> selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE
            R.id.radio_button_netwotk_any -> selectedNetworkOption = JobInfo.NETWORK_TYPE_ANY
            R.id.radio_button_netwotk_wifi -> selectedNetworkOption = JobInfo.NETWORK_TYPE_UNMETERED
            else -> selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE
        }

        mScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler

        val serviceName = ComponentName(
            packageName,
            NotificationJobService::class.java.name
        )
        val builder = JobInfo.Builder(JOB_ID, serviceName)
        builder.setRequiredNetworkType(selectedNetworkOption)

        val constraintSet = selectedNetworkOption !== JobInfo.NETWORK_TYPE_NONE
        if (constraintSet) {
            val myJobInfo = builder.build()
            mScheduler?.schedule(myJobInfo)
            Toast.makeText(this, "Job Scheduled, job will run when the constraints are met.", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "lease set at least one constraint.", Toast.LENGTH_SHORT).show()

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        canceljob()
    }

    private fun canceljob() {
        mScheduler?.let {
            mScheduler?.cancelAll()
            mScheduler = null
            Toast.makeText(this, "Jobs cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}
