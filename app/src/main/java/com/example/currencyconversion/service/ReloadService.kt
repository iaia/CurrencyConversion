package com.example.currencyconversion.service

import android.app.NotificationManager
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.currencyconversion.NOTIFICATION_CHANNEL_ID
import com.example.currencyconversion.R
import com.example.data.repository.LiveRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ReloadService : JobService() {
    val liveRepository: LiveRepository by inject()

    override fun onStopJob(params: JobParameters): Boolean {
        jobFinished(params, false)
        return false
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        Thread(
            Runnable {
                val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val notification =
                    NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
                        .apply {
                            setContentTitle("reload rates")
                            setSmallIcon(R.drawable.ic_launcher_background)
                        }.build()
                manager.notify(1, notification)

                GlobalScope.launch(Dispatchers.IO) {
                    liveRepository.getRates()
                }
                jobFinished(params, false)
            }
        ).start()
        return true
    }
}