package ec.edu.puce.githubclient.services

import ec.edu.puce.githubclient.models.Repository
import ec.edu.puce.githubclient.models.RepositoryPayload
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServices {
    @GET("/user/repos")
    suspend fun getRepositories(
        @Query( value = "affiliation") affiliation: String ="owner,collaborator,organization_member",
        @Query( value = "sort") sort: String ="created",
        @Query( value = "direction") direction: String ="desc",
        @Query( value = "per_page") perPage: Int = 100,
        @Query( value = "t") t: String = "${System.currentTimeMillis()}",
    ): List<Repository>
    @POST(value = "/user/repos")
    suspend fun createRepository(
        @Body repository: RepositoryPayload
    ): Repository
}