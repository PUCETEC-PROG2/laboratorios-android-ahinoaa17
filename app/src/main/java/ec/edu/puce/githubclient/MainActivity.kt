package ec.edu.puce.githubclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import ec.edu.puce.githubclient.models.Repository
import ec.edu.puce.githubclient.ui.screens.RepoForm
import ec.edu.puce.githubclient.ui.screens.RepoList
import ec.edu.puce.githubclient.ui.theme.GithubClientTheme
import ec.edu.puce.githubclient.viewmodels.RepoListViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: RepoListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var screen by remember { mutableStateOf("lista") }
            var selectedRepo by remember { mutableStateOf<Repository?>(null) }

            GithubClientTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    if (screen == "lista") {
                        RepoList(
                            onNavigateToForm = { repo ->
                                selectedRepo = repo
                                screen = "formulario"
                            },
                            viewModel = viewModel
                        )
                    } else {
                        RepoForm(
                            repository = selectedRepo,
                            onBackClick = {
                                selectedRepo = null
                                screen = "lista"
                            },
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}