package ec.edu.puce.githubclient.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ec.edu.puce.githubclient.models.Repository
import ec.edu.puce.githubclient.models.RepositoryPayload
import ec.edu.puce.githubclient.services.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepoListViewModel : ViewModel() {

    private val _repos = MutableStateFlow<List<Repository>>(emptyList())
    val repos: StateFlow<List<Repository>> = _repos.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMsg = MutableStateFlow<String?>(null)
    val errorMsg = _errorMsg.asStateFlow()

    init {
        fetchRepos()
    }

    fun fetchRepos() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMsg.value = null
            try {
                _repos.value = RetrofitClient.apiService.getRepositories()
            } catch (e: Exception) {
                _errorMsg.value = "Error al cargar repositorios: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun createRepo(name: String, description: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMsg.value = null
            try {
                val payload = RepositoryPayload(name = name, description = description)
                RetrofitClient.apiService.createRepository(payload)
                fetchRepos()
                onSuccess()
            } catch (e: Exception) {
                _errorMsg.value = "Error al crear repositorio: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateRepo(owner: String, oldName: String, newName: String, newDescription: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMsg.value = null
            try {
                val payload = RepositoryPayload(name = newName, description = newDescription)
                RetrofitClient.apiService.updateRepository(owner, oldName, payload)
                fetchRepos()
                onSuccess()
            } catch (e: Exception) {
                _errorMsg.value = "Error al actualizar repositorio: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteRepo(owner: String, repoName: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMsg.value = null
            try {
                RetrofitClient.apiService.deleteRepository(owner, repoName)
                fetchRepos()
            } catch (e: Exception) {
                _errorMsg.value = "Error al eliminar repositorio: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}