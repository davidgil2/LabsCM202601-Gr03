package co.edu.udea.compumovil.gr03_20261.lab2.data.model

data class Article(
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val source: Source
)

data class Source(
    val id: String?,
    val name: String
)

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)