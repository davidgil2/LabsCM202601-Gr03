package co.edu.udea.compumovil.gr03_20261.lab2.data.repository

import co.edu.udea.compumovil.gr03_20261.lab2.data.model.Article
import co.edu.udea.compumovil.gr03_20261.lab2.data.network.RetrofitInstance

class NewsRepository {
    suspend fun getHeadlines(): List<Article> {
        return RetrofitInstance.api.getHeadlines().articles
    }
}