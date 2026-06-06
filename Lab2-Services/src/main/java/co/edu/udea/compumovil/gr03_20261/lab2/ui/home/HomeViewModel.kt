package co.edu.udea.compumovil.gr03_20261.lab2.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.udea.compumovil.gr03_20261.lab2.data.model.Article
import co.edu.udea.compumovil.gr03_20261.lab2.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class HomeUiState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class HomeViewModel : ViewModel() {
    private val repository = NewsRepository()

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadNews()
    }

    fun loadNews() {
        viewModelScope.launch {
            _uiState.value = HomeUiState(isLoading = true)
            try {
                val articles = repository.getHeadlines()
                _uiState.value = HomeUiState(articles = articles)
            } catch (e: Exception) {
                _uiState.value = HomeUiState(error = "Error al cargar noticias: ${e.message}")
            }
        }
    }
}