package luis.barroso.androidstudyguide.androidCore

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_android_core_notifications_screen.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import luis.barroso.androidstudyguide.R

class AndroidCoreNotificationsScreenActivity : AppCompatActivity() {


    companion object {

        //Simple notification
        const val CHANEL_ID = "CHANEL_ID"
        const val CHANNEL_NAME = "CHANEL_NAME"
        const val CHANNEL_DESCRIPTION = "CHANEL_DESCRIPTION"
        const val NOTIFICATION_ID = 147852369

        //Loading notification
        const val CHANEL_ID_LOADING = "CHANEL_ID_LOADING"
        const val CHANNEL_NAME_LOADING = "CHANEL_NAME_LOADING"
        const val CHANNEL_DESCRIPTION_LOADING = "CHANEL_DESCRIPTION_LOADING"
        const val NOTIFICATION_ID_LOADING = 789654123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_core_notifications_screen)

        button_simple_notification.setOnClickListener { showSimpleNotification() }
        button_loading_notification.setOnClickListener { showLoadingNotification() }
    }

    private fun showSimpleNotification() {

        createNotificationChannel(
            CHANEL_ID,
            CHANNEL_NAME,
            CHANNEL_DESCRIPTION
        )

        var builder = NotificationCompat.Builder(this,
            CHANEL_ID
        )
            .setSmallIcon(R.drawable.ic_android)
            .setContentTitle(getText(R.string.android_core_screen_notification_simple_text))
            .setContentText(getText(R.string.android_core_screen_notification_simple_text_content))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    private fun showLoadingNotification() {
        createNotificationChannel(
            CHANEL_ID_LOADING,
            CHANNEL_NAME_LOADING,
            CHANNEL_DESCRIPTION_LOADING
        )

        val builder = NotificationCompat.Builder(this,
            CHANEL_ID_LOADING
        ).apply {
            setContentTitle(getText(R.string.android_core_screen_notification_loading_text))
            setContentText(getText(R.string.android_core_screen_notification_loading_content))
            setSmallIcon(R.drawable.ic_android)
            setPriority(NotificationCompat.PRIORITY_LOW)
        }
        val PROGRESS_MAX = 100
        val PROGRESS_CURRENT = 0

        NotificationManagerCompat.from(this).apply {
            //Init notification with initial value
            builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false)
            builder.setSound(null)
            notify(NOTIFICATION_ID_LOADING, builder.build())

            //Mock update
            runBlocking {
                val job = launch(Dispatchers.Default) {
                    for(iterationProgress in 0..10){
                        println("${Thread.currentThread()} has run.")
                        Thread.sleep(1000)

                        val progress = iterationProgress * 10
                        builder.setProgress(PROGRESS_MAX, progress,false)
                        notify(NOTIFICATION_ID_LOADING, builder.build())

                        if(progress == PROGRESS_MAX){
                            // When done, update the notification one more time to remove the progress bar
                            builder.setContentText("Download complete")
                                .setProgress(0, 0, false)
                            notify(NOTIFICATION_ID_LOADING, builder.build())
                        }
                    }

                }
            }
        }
    }

    private fun createNotificationChannel(channel: String, channelName: String, descriptionChannel: String) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channel, channelName, importance).apply {
                description = descriptionChannel
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
