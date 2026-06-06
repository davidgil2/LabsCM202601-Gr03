package co.edu.udea.compumovil.gr03_20261.lab2.data.network

import co.edu.udea.compumovil.gr03_20261.lab2.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String = "co",
        @Query("pageSize") pageSize: Int = 20,
        @Query("apiKey") apiKey: String = "873618d0bc114d5d8c0f88e08a46e154"
    ): NewsResponse
}

object RetrofitInstance {
    private const val BASE_URL = "https://newsapi.org/"

    val api: NewsApiService by lazy {
        retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }
}