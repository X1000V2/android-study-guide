package luis.barroso.androidstudyguide.androidCore.jetpack.workmanager

import android.content.Context
import android.os.Handler
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit.SECONDS

class DataWorkManager(appContext: Context, workerParams: WorkerParameters) : Worker(appContext,workerParams) {
    override fun doWork(): Result {
        //wait
        SECONDS.sleep(5)
        return Result.success()
    }

}