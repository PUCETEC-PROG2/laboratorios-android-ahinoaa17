package ec.edu.puce.githubclient.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ec.edu.puce.githubclient.ui.components.RepoItem

@Composable
fun RepoList() {
    Column(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 48.dp)
    ) {
        RepoItem(
            name = "Repositorio Django",
            description = "Proyecto de Python de Ahinoa",
            avatarImg = "https://marketplace.canva.com/MADaqpevv8c/1/thumbnail_large-1/canva-mountain-landscape-MADaqpevv8c.jpg",
            language = "Python"
        )
        RepoItem(
            name = "Repositorio de Prueba",
            description = "Proyecto de Ahinoa",
            avatarImg = "https://marketplace.canva.com/MADaqpevv8c/1/thumbnail_large-1/canva-mountain-landscape-MADaqpevv8c.jpg",
            language = "Python"
        )
        RepoItem(
            name = "Repositorio Prueba",
            description = "Proyecto de Ahinoa",
            avatarImg = "https://marketplace.canva.com/MADaqpevv8c/1/thumbnail_large-1/canva-mountain-landscape-MADaqpevv8c.jpg",
            language = "Python"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RepoListPreview() {
    RepoList()
}