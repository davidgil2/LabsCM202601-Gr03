package co.edu.udea.compumovil.gr03_20261.lab2.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import co.edu.udea.compumovil.gr03_20261.lab2.data.repository.NewsRepository

class NewsSyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            val repository = NewsRepository()
            val articles = repository.getHeadlines()

            android.util.Log.d("NewsSyncWorker", "Sincronizadas ${articles.size} noticias")
            Result.success()
        } catch (e: Exception) {
            android.util.Log.e("NewsSyncWorker", "Error al sincronizar: ${e.message}")
            Result.retry()
        }
    }
}