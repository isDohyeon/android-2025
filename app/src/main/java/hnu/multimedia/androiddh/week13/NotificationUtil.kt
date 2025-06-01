package hnu.multimedia.androiddh.week13

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import hnu.multimedia.androiddh.R

class NotificationUtil {

    companion object {
        private val CHANNEL_ID = "make_notification_test"
        private val CHANNEL_NAME = "channelName"
        private val CHANNEL_DESC = "channelDesc"

        fun createNotification(context: Context, title: String, message: String, url: String, importance: Int) {
            val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val builder : NotificationCompat.Builder

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val mChannel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    importance
                )
                mChannel.description = CHANNEL_DESC
                notificationManager.createNotificationChannel(mChannel)
                builder = NotificationCompat.Builder(context, CHANNEL_ID)
            } else {
                builder = NotificationCompat.Builder(context)
            }

            val fixedUrl = if (!url.startsWith("http://") && !url.startsWith("https://")) {
                "https://$url"
            } else {
                url
            }

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(fixedUrl))

            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            builder.setSmallIcon(R.drawable.notification)
            builder.setPriority(NotificationCompat.PRIORITY_HIGH)
            builder.setContentTitle(title)
            builder.setContentText(message)
            builder.setContentIntent(pendingIntent)
            builder.setAutoCancel(true)

            notificationManager.notify(1, builder.build())
        }

        fun requestPermissions(activity: AppCompatActivity) {
            val isTiramisuOrHigher = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
            val permission = Manifest.permission.POST_NOTIFICATIONS

            var hasPermission =
                if (isTiramisuOrHigher) {
                    ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
                } else {
                    true
                }
            val launcher = activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                hasPermission = it
            }

            if (!hasPermission) {
                launcher.launch(permission)
            }
        }
    }
}