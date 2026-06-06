package co.edu.udea.compumovil.gr03_20261.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import co.edu.udea.compumovil.gr03_20261.lab2.navigation.AppNavigation
import co.edu.udea.compumovil.gr03_20261.lab2.ui.theme.Labs20261Gr03Theme
import co.edu.udea.compumovil.gr03_20261.lab2.worker.NewsSyncWorker
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        scheduleNewsSync()

        setContent {
            Labs20261Gr03Theme {
                AppNavigation()
            }
        }
    }

    private fun scheduleNewsSync() {
        val workRequest = PeriodicWorkRequestBuilder<NewsSyncWorker>(
            15, TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "news_sync",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}
